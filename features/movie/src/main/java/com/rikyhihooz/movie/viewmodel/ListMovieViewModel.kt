package com.rikyhihooz.movie.viewmodel

import androidx.lifecycle.*
import com.rikyhihooz.common.base.BaseViewModel
import com.rikyhihooz.common.utils.ErrorHandler
import com.rikyhihooz.common.utils.Event
import com.rikyhihooz.model.MovieModel
import com.rikyhihooz.model.Result
import com.rikyhihooz.movie.fragment.ListMovieFragmentDirections
import com.rikyhihooz.movie.service.MovieService
import com.rikyhihooz.repository.utils.AppDispatchers
import com.rikyhihooz.repository.utils.Resource
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ListMovieViewModel(private val service:MovieService,
                         private val dispatchers:AppDispatchers) : BaseViewModel(){

    val searchLoading: MutableLiveData<Boolean> = MutableLiveData()

    val _listMovie = MediatorLiveData<Resource<MovieModel>>()
    var listMovieResource: LiveData<Resource<MovieModel>> = MutableLiveData()
    val listMovieData: LiveData<Resource<MovieModel>> get() = _listMovie
    val loaderListMovie: MutableLiveData<Boolean> = MutableLiveData()

    init {
        getListMovie(0, 1, true)
    }

    fun getListMovie(filter:Int, page:Int, shouldFetch:Boolean) {
        viewModelScope.launch(dispatchers.main) {
            _listMovie.removeSource(listMovieResource)
            withContext(dispatchers.io) {
                listMovieResource = service(filter, page, shouldFetch)
            }
            _listMovie.addSource(listMovieResource) {
                _listMovie.postValue(it)
                loaderListMovie.value = (it.status == Resource.Status.LOADING)
                when (it.status) {
                    Resource.Status.SUCCESS -> _listMovie.postValue(it)
                    Resource.Status.ERROR -> _errorHandler.postValue(Event(ErrorHandler(ErrorHandler.ErrorType.LAYOUT, it.error)))
                }
            }
        }

    }

    fun onDetailMovie(result:Result){
        navigateTo(ListMovieFragmentDirections.listMovieToDetailMovie(result))
    }

    fun onFavoritMovie(){
        navigateTo(ListMovieFragmentDirections.listMovieToListFavoritMovie())
    }

}
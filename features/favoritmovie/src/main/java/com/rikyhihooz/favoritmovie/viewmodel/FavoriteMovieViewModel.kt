package com.rikyhihooz.favoritmovie.viewmodel

import androidx.lifecycle.LiveData
import com.rikyhihooz.common.base.BaseViewModel
import com.rikyhihooz.favoritmovie.fragment.ListFavoritMovieFragmentDirections
import com.rikyhihooz.favoritmovie.service.FavoriteService
import com.rikyhihooz.model.Result

class FavoriteMovieViewModel(private val service:FavoriteService):BaseViewModel() {

    init {
        getData()
    }

    lateinit var data : LiveData<List<Result>>

    fun getData(){
        data = service.getListFavorite()
    }

    fun onDetailMovie(it: Result) {
        navigateTo(ListFavoritMovieFragmentDirections.listMovieToDetailMovie(it))
    }

}
package com.rikyhihooz.movie.service

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.rikyhihooz.model.MovieModel
import com.rikyhihooz.repository.MovieRepositoryInterface
import com.rikyhihooz.repository.utils.Resource

class MovieService(private val repository: MovieRepositoryInterface) {

    suspend operator fun invoke(filter:Int, page:Int, shouldFetch:Boolean) : LiveData<Resource<MovieModel>>{
        return Transformations.map(repository.getListMovie(filter, page, shouldFetch)) { it }
    }

    suspend fun getReviewMovie(idMovie:String)
            = Transformations.map(repository.getReviewMovie(idMovie)){it}

    fun getResult(id:Int?) = repository.getResult(id)

    fun updaResulte(result:Boolean?, id: Int?) = repository.updateFavorite(result,id)
}
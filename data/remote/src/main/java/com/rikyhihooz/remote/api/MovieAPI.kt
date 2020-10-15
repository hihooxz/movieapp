package com.rikyhihooz.remote.api

import com.rikyhihooz.model.MovieModel
import com.rikyhihooz.model.ReviewMovie
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface MovieAPI {

    @GET("movie/{filter}")
    suspend fun getListMovie(@Path("filter") filter:String,
                             @QueryMap query:HashMap<String, Any>):Response<MovieModel>

    @GET("movie/{idmovie}/reviews")
    suspend fun getReviewMovie(@Path("idmovie") id:String?,
                               @QueryMap query: HashMap<String, Any>):Response<ReviewMovie>

}
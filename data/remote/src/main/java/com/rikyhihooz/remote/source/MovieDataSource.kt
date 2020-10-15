package com.rikyhihooz.remote.source

import com.rikyhihooz.model.MovieModel
import com.rikyhihooz.model.ReviewMovie
import com.rikyhihooz.remote.BuildConfig
import com.rikyhihooz.remote.api.MovieAPI
import com.rikyhihooz.remote.utils.Constans.API_KEY
import com.rikyhihooz.remote.utils.Constans.NOW_PLAYING_MOVIE_URL
import com.rikyhihooz.remote.utils.Constans.PAGE
import com.rikyhihooz.remote.utils.Constans.POPULER_MOVIE
import com.rikyhihooz.remote.utils.Constans.POPULER_MOVIE_URL
import com.rikyhihooz.remote.utils.Constans.TOP_RATE_MOVIE
import com.rikyhihooz.remote.utils.Constans.TOP_RATE_MOVIE_URL
import com.rikyhihooz.remote.utils.Constans.UPCOMING_MOVIE
import com.rikyhihooz.remote.utils.Constans.UPCOMING_MOVIE_URL
import retrofit2.Response

class MovieDataSource(val api:MovieAPI) {

    suspend fun getListMovie(filter:Int, page:Int): Response<MovieModel> {
        val query=HashMap<String, Any>()
        query[PAGE] = page
        query[API_KEY] = BuildConfig.API_KEY

        return when(filter){
            POPULER_MOVIE -> api.getListMovie(POPULER_MOVIE_URL, query)
            UPCOMING_MOVIE -> api.getListMovie(UPCOMING_MOVIE_URL, query)
            TOP_RATE_MOVIE -> api.getListMovie(TOP_RATE_MOVIE_URL, query)
            else -> api.getListMovie(NOW_PLAYING_MOVIE_URL, query)
        }
    }

    suspend fun getListReviewMovie(idMovie:String?):Response<ReviewMovie>{
        val query = HashMap<String, Any>()
        query[API_KEY] = BuildConfig.API_KEY
        return api.getReviewMovie(idMovie, query)
    }
}
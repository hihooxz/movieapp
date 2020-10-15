package com.rikyhihooz.favoritmovie.service

import com.rikyhihooz.repository.MovieRepositoryInterface

class FavoriteService(private val listener:MovieRepositoryInterface) {

    fun getListFavorite()=listener.getListFavorite()
}
package com.rikyhihooz.favoritmovie.di

import com.rikyhihooz.favoritmovie.service.FavoriteService
import com.rikyhihooz.favoritmovie.viewmodel.FavoriteMovieViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val favoriteModule = module {
    factory { FavoriteService(get()) }
    viewModel { FavoriteMovieViewModel(get()) }
}
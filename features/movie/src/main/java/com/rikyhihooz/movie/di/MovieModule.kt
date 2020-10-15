package com.rikyhihooz.movie.di

import com.rikyhihooz.movie.service.MovieService
import com.rikyhihooz.movie.viewmodel.CategoryMovieViewModel
import com.rikyhihooz.movie.viewmodel.DetailMovieViewModel
import com.rikyhihooz.movie.viewmodel.ListMovieViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val listMovieModule = module {

    factory { MovieService(get()) }

    viewModel { ListMovieViewModel(get(),get()) }
    viewModel { DetailMovieViewModel(get(), get()) }
    viewModel { CategoryMovieViewModel(get()) }
}
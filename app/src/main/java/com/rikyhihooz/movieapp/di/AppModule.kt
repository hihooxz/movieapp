package com.rikyhihooz.movieapp.di

import com.rikyhihooz.favoritmovie.di.favoriteModule
import com.rikyhihooz.local.di.localModule
import com.rikyhihooz.movie.di.listMovieModule
import com.rikyhihooz.remote.di.remoteModule
import com.rikyhihooz.repository.di.repositoryModule
import org.koin.core.module.Module

val appComponent : List<Module> = listOf(
    remoteModule,
    localModule,
    repositoryModule,
    listMovieModule,
    favoriteModule
)
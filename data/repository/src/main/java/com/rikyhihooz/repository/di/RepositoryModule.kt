package com.rikyhihooz.repository.di

import com.rikyhihooz.repository.MovieRepository
import com.rikyhihooz.repository.MovieRepositoryInterface
import com.rikyhihooz.repository.utils.AppDispatchers
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

val repositoryModule = module {

    factory { AppDispatchers(Dispatchers.Main, Dispatchers.IO) }
    factory { MovieRepository(get(), get(), get()) as MovieRepositoryInterface }
}
package com.rikyhihooz.remote.di

import com.google.gson.GsonBuilder
import com.rikyhihooz.remote.BuildConfig
import com.rikyhihooz.remote.api.MovieAPI
import com.rikyhihooz.remote.source.MovieDataSource
import com.rikyhihooz.remote.utils.Constans.TIMEOUT
import com.rikyhihooz.remote.utils.DataTypeAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

const val LOGGING_INTERCEPTOR = "LOGGING_INTERCEPTOR"

val remoteModule = module{

    single<Interceptor>(named(LOGGING_INTERCEPTOR)) {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.apply {
            if(BuildConfig.DEBUG)level = HttpLoggingInterceptor.Level.BODY
        }
    }

    single {
        GsonBuilder()
            .registerTypeAdapterFactory(DataTypeAdapterFactory())
            .setLenient()
            .create()
    }

    factory {
        OkHttpClient.Builder()
            .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(get(named(LOGGING_INTERCEPTOR)))
            .retryOnConnectionFailure(true)
            .build()
    }

    single {
        Retrofit.Builder()
            .client(get())
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(get()))
            .build()
    }

    factory { get<Retrofit>().create(MovieAPI::class.java) }

    factory { MovieDataSource(get()) }
}
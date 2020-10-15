package com.rikyhihooz.local.di

import com.rikyhihooz.local.AppDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module

private const val DATABASE = "DATABASE"

val localModule = module {
    single(named(DATABASE)) { AppDatabase.buildDatabase(androidContext()) }

    factory { (get(named(DATABASE)) as AppDatabase).movieDao() }
}
package nh.hackaton.forkids.di

import nh.hackaton.forkids.util.AppPreference
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val localDataSourceModule = module {
    single { AppPreference(androidApplication().applicationContext) }
}

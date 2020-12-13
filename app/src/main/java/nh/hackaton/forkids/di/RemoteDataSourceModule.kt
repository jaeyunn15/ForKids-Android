package nh.hackaton.forkids.di

import nh.hackaton.forkids.network.datasource.*
import org.koin.dsl.module

val remoteDataSourceModule = module {
    single<UserRemoteDataSource>{
        UserRemoteDataSourceImpl(get())
    }

    single<AccountRemoteDataSource>{
        AccountRemoteDataSourceImpl(get())
    }

    single<EduRemoteDataSource>{
        EduRemoteDataSourceImpl(get())
    }

}
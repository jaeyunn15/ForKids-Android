package nh.hackaton.forkids.di

import nh.hackaton.forkids.network.datasource.AccountRemoteDataSource
import nh.hackaton.forkids.network.datasource.AccountRemoteDataSourceImpl
import nh.hackaton.forkids.network.datasource.UserRemoteDataSource
import nh.hackaton.forkids.network.datasource.UserRemoteDataSourceImpl
import org.koin.dsl.module

val remoteDataSourceModule = module {
    single<UserRemoteDataSource>{
        UserRemoteDataSourceImpl(get())
    }

    single<AccountRemoteDataSource>{
        AccountRemoteDataSourceImpl(get())
    }
    
}
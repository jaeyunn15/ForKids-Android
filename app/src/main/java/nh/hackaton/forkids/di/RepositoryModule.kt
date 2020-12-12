package nh.hackaton.forkids.di

import nh.hackaton.forkids.network.repository.AccountRepo
import nh.hackaton.forkids.network.repository.AccountRepoImpl
import nh.hackaton.forkids.network.repository.UserRepo
import nh.hackaton.forkids.network.repository.UserRepoImpl
import org.koin.dsl.module

val repositoryModule = module {
    single<UserRepo>{
        UserRepoImpl(get())
    }
    single<AccountRepo>{
        AccountRepoImpl(get())
    }
}
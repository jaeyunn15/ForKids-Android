package nh.hackaton.forkids.di

import nh.hackaton.forkids.network.repository.*
import org.koin.dsl.module

val repositoryModule = module {
    single<UserRepo>{
        UserRepoImpl(get())
    }
    single<AccountRepo>{
        AccountRepoImpl(get())
    }
    single<EduRepo>{
        EduRepoImpl(get())
    }
}
package nh.hackaton.forkids

import android.app.Application
import nh.hackaton.forkids.di.*
import nh.hackaton.forkids.util.setupKoin

class ForkidsApplication : Application(){
    override fun onCreate() {
        super.onCreate()
        setupKoin(
            this,
            localDataSourceModule,
            networkModule,
            remoteDataSourceModule,
            repositoryModule,
            viewModelModule
        )
    }
}
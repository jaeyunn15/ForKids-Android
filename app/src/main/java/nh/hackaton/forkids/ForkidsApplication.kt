package nh.hackaton.forkids

import android.app.Application
import nh.hackaton.forkids.di.networkModule
import nh.hackaton.forkids.di.remoteDataSourceModule
import nh.hackaton.forkids.di.repositoryModule
import nh.hackaton.forkids.di.viewModelModule
import nh.hackaton.forkids.util.setupKoin

class ForkidsApplication : Application(){
    override fun onCreate() {
        super.onCreate()
        setupKoin(
            this,
            networkModule,
            remoteDataSourceModule,
            repositoryModule,
            viewModelModule
        )
    }
}
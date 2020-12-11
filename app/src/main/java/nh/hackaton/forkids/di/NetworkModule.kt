package nh.hackaton.forkids.di

import okhttp3.OkHttpClient
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {

    val BASE_URL = "http://54.180.87.117"

//    single<API> {
//        Retrofit.Builder()
//            .baseUrl(BASE_URL)
//            .client(get(named("okHttp")))
//            .addCallAdapterFactory(RxJava2CallAdapterFactory.createAsync())
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//            .create(API::class.java)
//    }

    single(named("okHttp")) {
        OkHttpClient.Builder().apply {
            connectTimeout(60, TimeUnit.SECONDS)
            readTimeout(30, TimeUnit.SECONDS)
        }.build()
    }
}


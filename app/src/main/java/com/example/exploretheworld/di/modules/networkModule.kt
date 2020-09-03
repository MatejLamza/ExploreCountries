package com.example.exploretheworld.di.modules

import com.example.exploretheworld.BuildConfig
import com.example.exploretheworld.data.remote.services.APIService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModules = module {
    single {
        GsonBuilder()
            .setLenient()
            .create()
    }

    single<GsonConverterFactory> {
        GsonConverterFactory.create(get<Gson>())
    }

    single {
        OkHttpClient()
            .newBuilder()
            .readTimeout(10, TimeUnit.SECONDS)
            .build()
    }

    single {
        Retrofit.Builder()
            .baseUrl(get<String>(Qualifiers.apiFullURL))
            .addConverterFactory(get<GsonConverterFactory>())
            .client(get())
            .build()
    }

    single(Qualifiers.apiFullURL) { BuildConfig.API_URL }

    single { get<Retrofit>().create(APIService::class.java) }
}
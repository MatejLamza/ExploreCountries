package com.example.exploretheworld.di.modules

import com.example.exploretheworld.data.remote.services.APIService
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://private-9c4b85-geotest1.apiary-mock.com"

val appModule = module {
    fun provideAPIService(): APIService {
        val okHttpClient = OkHttpClient.Builder()
            .build()
        val gson = GsonBuilder().setLenient().create()

        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(APIService::class.java)
    }

    single { provideAPIService() }
}
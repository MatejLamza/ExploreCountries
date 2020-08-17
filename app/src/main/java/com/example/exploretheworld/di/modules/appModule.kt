package com.example.exploretheworld.di.modules

import com.example.exploretheworld.data.local.database.ExploreTheWorldDAO
import com.example.exploretheworld.data.local.database.ExploreTheWorldDatabase
import com.example.exploretheworld.data.remote.services.APIService
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://private-9c4b85-geotest1.apiary-mock.com"
private const val DB_NAME = "ExploreTheWorldDatabase"

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

//    fun provideExploreTheWorldDatabase(context: Context): ExploreTheWorldDatabase {
//        return Room.databaseBuilder(context, ExploreTheWorldDatabase::class.java, DB_NAME).build()
//    }

    fun provideExploreTheWorldDAO(database: ExploreTheWorldDatabase): ExploreTheWorldDAO {
        return database.getExploreTheWorldDAO()
    }

//    single { provideExploreTheWorldDAO(database = get()) }

//    factory { provideExploreTheWorldDatabase(context = get()) }

    single { provideAPIService() }
}
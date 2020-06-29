package com.example.exploretheworld.di.modules

import com.example.exploretheworld.data.remote.datasource.APIDataSource
import com.example.exploretheworld.data.remote.datasource.APIDataSourceImpl
import com.example.exploretheworld.data.remote.services.APIService
import com.example.exploretheworld.data.repositories.DataRepository
import org.koin.dsl.module

val dataSourceModule = module {
    fun provideDataSource(apiService: APIService): APIDataSource {
        return APIDataSourceImpl(apiService)
    }

    single { provideDataSource(apiService = get()) }
    single { DataRepository(apiDataSource = get()) }
}
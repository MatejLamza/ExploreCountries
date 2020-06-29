package com.example.exploretheworld.data.repositories

import com.example.exploretheworld.data.remote.datasource.APIDataSource

class DataRepository(private val apiDataSource: APIDataSource) {
    suspend fun fetchAllCountries() = apiDataSource.getCountries()
}
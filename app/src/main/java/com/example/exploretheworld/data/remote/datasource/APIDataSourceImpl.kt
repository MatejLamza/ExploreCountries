package com.example.exploretheworld.data.remote.datasource

import com.example.exploretheworld.data.models.City
import com.example.exploretheworld.data.models.Country
import com.example.exploretheworld.data.remote.services.APIService
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext

class APIDataSourceImpl(private val api: APIService) : APIDataSource {

    override suspend fun getCountries(): List<Country> {
        return withContext(IO) {
            api.fetchCountries()
        }
    }

    override suspend fun getTop10Cities(): List<City> {
        return withContext(IO) {
            api.fetchTop10Cities()
        }
    }
}
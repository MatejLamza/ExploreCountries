package com.example.exploretheworld.data.remote.datasource

import androidx.lifecycle.LiveData
import com.example.exploretheworld.data.models.City
import com.example.exploretheworld.data.models.Country

interface APIDataSource {

    val downloadedCountries: LiveData<List<Country>>

    suspend fun getCountries(): List<Country>
    suspend fun getTop10Cities(): List<City>
}
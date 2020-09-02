package com.example.exploretheworld.data.remote.datasource

import com.example.exploretheworld.data.models.City
import com.example.exploretheworld.data.models.Country

/**
 * Just layer of abstraction over Repository.
 */
interface APIDataSource {
    suspend fun getCountries(): List<Country>
    suspend fun getTop10Cities(): List<City>
}
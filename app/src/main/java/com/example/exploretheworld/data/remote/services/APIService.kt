package com.example.exploretheworld.data.remote.services

import com.example.exploretheworld.data.models.City
import com.example.exploretheworld.data.models.Country
import retrofit2.http.GET

interface APIService {

    /**
     * Simple GET call on api that takes given string "/countries" and merge it with base URL that retrofit instance has.
     */
    @GET("/countries")
    suspend fun fetchCountries(): List<Country>

    @GET("/top10cities")
    suspend fun fetchTop10Cities(): List<City>
}
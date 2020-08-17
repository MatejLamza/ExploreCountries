package com.example.exploretheworld.data.remote.services

import com.example.exploretheworld.data.models.City
import com.example.exploretheworld.data.models.Country
import retrofit2.http.GET

interface APIService {

    @GET("/countries")
    suspend fun fetchCountries(): List<Country>

    @GET("/top10cities")
    suspend fun fetchTop10Cities(): List<City>
}
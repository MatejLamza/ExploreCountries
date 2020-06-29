package com.example.exploretheworld.data.remote.services

import com.example.exploretheworld.data.models.Country
import retrofit2.http.GET

interface APIService {

    @GET("/countries")
    suspend fun fetchCountries():List<Country>
}
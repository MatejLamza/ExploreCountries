package com.example.exploretheworld.data.models

data class City(
    val id: Int,
    val city: String,
    val country: String,
    val population: Long,
    val latlng: List<Double>,
    val description: String
)
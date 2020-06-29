package com.example.exploretheworld.data.models

data class Country(
    val name: String,
    val capital: String,
    val region: String,
    val population: Long,
    val flag: String,
    val latLng: List<Float>,
    val description: String
)
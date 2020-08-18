package com.example.exploretheworld.utils.mappers

import com.example.exploretheworld.data.models.City
import com.example.exploretheworld.data.models.Country
import com.example.exploretheworld.data.models.ListCities
import com.example.exploretheworld.data.models.ListCountry


fun ListCountry.mapToList(): List<Country> {
    return this.countries
}

fun ListCities.mapToLocalModel(): List<City> {
    return this.cities
}

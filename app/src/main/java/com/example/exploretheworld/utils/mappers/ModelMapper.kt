package com.example.exploretheworld.utils.mappers

import com.example.exploretheworld.data.models.Country
import com.example.exploretheworld.data.models.ListCountry


fun ListCountry.mapToList(): List<Country> {
    return this.countries
}

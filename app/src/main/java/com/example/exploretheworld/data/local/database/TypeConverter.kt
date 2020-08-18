package com.example.exploretheworld.data.local.database

import androidx.room.TypeConverter
import com.example.exploretheworld.data.models.City
import com.example.exploretheworld.data.models.Country
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class TypeConverter {
    @TypeConverter
    fun fromCountriesList(countries: List<Country>) =
        Gson().toJson(countries, object : TypeToken<List<Country>>() {}.type)

    @TypeConverter
    fun toCountiesList(countries: String): List<Country> {
        val type = object : TypeToken<List<Country>>() {}.type
        return Gson().fromJson(countries, type)
    }

    @TypeConverter
    fun fromCities(cities: List<City>) =
        Gson().toJson(cities, object : TypeToken<List<City>>() {}.type)

    @TypeConverter
    fun toCityList(cities: String): List<City> {
        val type = object : TypeToken<List<City>>() {}.type
        return Gson().fromJson(cities, type)
    }
}
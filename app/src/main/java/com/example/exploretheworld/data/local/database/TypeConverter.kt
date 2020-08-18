package com.example.exploretheworld.data.local.database

import androidx.room.TypeConverter
import com.example.exploretheworld.data.models.City
import com.example.exploretheworld.data.models.Country
import com.example.exploretheworld.data.models.ListCountry
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class TypeConverter {

    @TypeConverter
    fun fromCountries(counties: ListCountry): String =
        Gson().toJson(counties, object : TypeToken<ListCountry>() {}.type)

    @TypeConverter
    fun toCountreis(countriesString: String): ListCountry =
        Gson().fromJson(countriesString, object : TypeToken<ListCountry>() {}.type)

    @TypeConverter
    fun toCountry(countryString: String): Country =
        Gson().fromJson(countryString, object : TypeToken<Country>() {}.type)

    @TypeConverter
    fun fromCountry(country: Country): String =
        Gson().toJson(country, object : TypeToken<Country>() {}.type)

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
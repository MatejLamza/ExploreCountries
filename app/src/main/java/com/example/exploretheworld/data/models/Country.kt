package com.example.exploretheworld.data.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Country(
    val name: String,
    val capital: String,
    val region: String,
    val population: Long,
    val flag: String,
    val latlng: List<Double>,
    val description: String,
    val language: List<String>
) : Parcelable

@Parcelize
data class ListCountry(
    val countries: List<Country>
) : Parcelable
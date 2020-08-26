package com.example.exploretheworld.data.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

const val CURRENT_COUNTRIES_ID = 0

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
@Entity(tableName = "Countries")
data class ListCountry(
    val countries: List<Country>
) : Parcelable {
    @PrimaryKey(autoGenerate = false)
    var idCountries = CURRENT_COUNTRIES_ID
}
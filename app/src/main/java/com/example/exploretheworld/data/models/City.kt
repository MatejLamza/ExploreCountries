package com.example.exploretheworld.data.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

const val CURRENT_CITY_ID = 0


@Parcelize
data class City(
    val id: Int,
    val city: String,
    val country: String,
    val population: Long,
    val latlng: List<Double>,
    val description: String
) : Parcelable

@Parcelize
@Entity(tableName = "City")
data class ListCities(
    val cities: List<City>
) : Parcelable {
    @PrimaryKey(autoGenerate = false)
    var idCities = CURRENT_CITY_ID
}
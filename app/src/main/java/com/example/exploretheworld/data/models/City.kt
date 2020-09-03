package com.example.exploretheworld.data.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

const val CURRENT_CITY_ID = 0

/**
 * In clean architecture we should separate models into 3 different layers. Domain, Database and Presentation layer.
 * For simplicity this app only separates domain model and database model. For presenting data to user application is
 * using Domain model.
 */
@Parcelize
data class City(
    val id: Int,
    val city: String,
    val country: String,
    val population: Long,
    val latlng: List<Double>,
    val background: String,
    val description: String
) : Parcelable

/**
 * Database model where entity is name of a table in which we are storing data and Primary key which will not be changed
 * because we are using Room as caching mechanism.
 */
@Parcelize
@Entity(tableName = "City")
data class ListCities(
    val cities: List<City>
) : Parcelable {
    @PrimaryKey(autoGenerate = false)
    var idCities = CURRENT_CITY_ID
}
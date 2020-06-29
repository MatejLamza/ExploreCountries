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
    val latLng: List<Float>,
    val description: String,
    val language: List<String>
) : Parcelable
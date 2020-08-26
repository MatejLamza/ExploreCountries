package com.example.exploretheworld.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.exploretheworld.data.models.ListCities
import com.example.exploretheworld.data.models.ListCountry

@Database(entities = [ListCountry::class, ListCities::class], version = 1, exportSchema = false)
@TypeConverters(TypeConverter::class)
abstract class ExploreTheWorldDatabase : RoomDatabase() {
    abstract fun getExploreTheWorldDAO(): ExploreTheWorldDAO
}
package com.example.exploretheworld.data.local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.exploretheworld.data.models.CURRENT_COUNTRIES_ID
import com.example.exploretheworld.data.models.ListCountry

@Dao
interface ExploreTheWorldDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertCountries(countries: ListCountry)

    @Query("SELECT * FROM countries WHERE idCountries = $CURRENT_COUNTRIES_ID")
    suspend fun getCountriesFromDatabse(): ListCountry?
}
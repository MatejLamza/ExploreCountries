package com.example.exploretheworld.data.local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.exploretheworld.data.models.CURRENT_CITY_ID
import com.example.exploretheworld.data.models.CURRENT_COUNTRIES_ID
import com.example.exploretheworld.data.models.ListCities
import com.example.exploretheworld.data.models.ListCountry

@Dao
interface ExploreTheWorldDAO {

    /**
     * OnConflictStrategy.REPLACE under the hood tells room if there is anything in this table, remove it and insert
     * new data. This way we have caching mechanism instead of giant database filled with older information.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertCountries(countries: ListCountry)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertTop10Cities(cities: ListCities)

    /**
     * Because we know we only have 1 record in database our CURRENT_COUNTRIES_ID is always same and we are just fetching that
     * latest record.
     */
    @Query("SELECT * FROM countries WHERE idCountries = $CURRENT_COUNTRIES_ID")
    suspend fun getCountriesFromDatabse(): ListCountry

    @Query("SELECT * FROM City WHERE idCities = $CURRENT_CITY_ID")
    suspend fun getCitiesFromDatabase(): ListCities
}
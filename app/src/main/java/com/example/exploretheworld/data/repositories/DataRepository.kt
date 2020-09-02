package com.example.exploretheworld.data.repositories

import com.example.exploretheworld.data.local.database.ExploreTheWorldDAO
import com.example.exploretheworld.data.remote.datasource.APIDataSource
import com.example.exploretheworld.utils.mappers.mapToList
import com.example.exploretheworld.utils.mappers.mapToListCity
import com.example.exploretheworld.utils.mappers.mapToListCountry
import com.example.exploretheworld.utils.mappers.mapToLocalModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext

/**
 * Repository acts as a single source of truth and it doesen't care from where data is coming he is only responsible
 * to provide data from whichever source it sees fit.
 */
class DataRepository(
    private val apiDataSource: APIDataSource,
    private val database: ExploreTheWorldDAO
) {

    /**
     * This function runs on IO thread and it fetches countries and cities from API and saves them directly into database.
     * This will be executed every time user starts application during splash screen, so there is new data every time user
     * enters application.
     */
    suspend fun fetchAndSaveDataToDatabase() {
        withContext(IO) {
            database.upsertCountries(apiDataSource.getCountries().mapToListCountry())
            database.upsertTop10Cities(apiDataSource.getTop10Cities().mapToListCity())
        }
    }

    /**
     * Because we previously fetched data from API
     */
    suspend fun getAllCountries() = database.getCountriesFromDatabse().mapToList()
    suspend fun getAllCities() = database.getCitiesFromDatabase().mapToLocalModel()
}
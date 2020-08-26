package com.example.exploretheworld.data.repositories

import com.example.exploretheworld.data.local.database.ExploreTheWorldDAO
import com.example.exploretheworld.data.models.ListCities
import com.example.exploretheworld.data.models.ListCountry
import com.example.exploretheworld.data.remote.datasource.APIDataSource
import com.example.exploretheworld.utils.mappers.mapToList
import com.example.exploretheworld.utils.mappers.mapToLocalModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class DataRepository(
    private val apiDataSource: APIDataSource,
    private val database: ExploreTheWorldDAO
) {

    /**
     * This ensures that every time application starts new data is fetched and being stored in database.
     */
    init {
        GlobalScope.launch {
            apiDataSource.getCountries()
            apiDataSource.getTop10Cities()
        }
        apiDataSource.downloadedCountries.observeForever { countries ->
            if (countries != null) {
                persistDownloadedCountries(ListCountry(countries))
            }
        }
        apiDataSource.downloadedTop10Cities.observeForever { downloadedCities ->
            if (downloadedCities != null) {
                persistDownloadedTop10Cities(ListCities(downloadedCities))
            }
        }
    }

    /**
     * When first time opening application database will be empty. In that case we can ensure that we get data from api.
     */
    suspend fun fetchAllCountries() =
        if (database.getCountriesFromDatabse() == null) apiDataSource.getCountries() else database.getCountriesFromDatabse()
            ?.mapToList().orEmpty()

    suspend fun fetchTop10Cities() =
        if (database.getCitiesFromDatabase() == null) apiDataSource.getTop10Cities() else database.getCitiesFromDatabase()
            ?.mapToLocalModel().orEmpty()

    private fun persistDownloadedCountries(countries: ListCountry) {
        GlobalScope.launch(IO) {
            database.upsertCountries(countries)
        }
    }

    private fun persistDownloadedTop10Cities(cities: ListCities) {
        GlobalScope.launch(IO) {
            database.upsertTop10Cities(cities)
        }
    }
}
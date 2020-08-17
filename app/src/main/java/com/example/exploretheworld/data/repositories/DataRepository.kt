package com.example.exploretheworld.data.repositories

import com.example.exploretheworld.data.local.database.ExploreTheWorldDAO
import com.example.exploretheworld.data.models.ListCountry
import com.example.exploretheworld.data.remote.datasource.APIDataSource
import com.example.exploretheworld.utils.mappers.mapToList
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class DataRepository(
    private val apiDataSource: APIDataSource,
    private val database: ExploreTheWorldDAO
) {

    init {
        apiDataSource.downloadedCountries.observeForever {
            if (it != null) {
                persistDownloadedCountries(ListCountry(it))
            }
        }
    }

    suspend fun fetchAllCountries() =
        if (database.getCountriesFromDatabse() == null) apiDataSource.getCountries() else database.getCountriesFromDatabse()
            ?.mapToList().orEmpty()

    suspend fun fetchTop10Cities() = apiDataSource.getTop10Cities()


    private fun persistDownloadedCountries(countries: ListCountry) {
        GlobalScope.launch(IO) {
            database.upsertCountries(countries)
        }
    }
}
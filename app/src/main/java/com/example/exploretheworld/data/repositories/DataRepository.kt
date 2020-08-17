package com.example.exploretheworld.data.repositories

import com.example.exploretheworld.data.remote.datasource.APIDataSource

class DataRepository(
    private val apiDataSource: APIDataSource
) {

//    init {
//        apiDataSource.downloadedCountries.observeForever {
//            if (it != null) {
//                persistDownloadedCountries(ListCountry(it))
//            }
//        }
//    }

    suspend fun fetchAllCountries() = apiDataSource.getCountries()
    suspend fun fetchTop10Cities() = apiDataSource.getTop10Cities()

//    private fun persistDownloadedCountries(countries: ListCountry) {
//        GlobalScope.launch(IO) {
//            database.upsertCountries(countries)
//        }
//    }
}
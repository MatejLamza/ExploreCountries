package com.example.exploretheworld.data.remote.datasource

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.exploretheworld.data.models.City
import com.example.exploretheworld.data.models.Country
import com.example.exploretheworld.data.remote.services.APIService
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext

class APIDataSourceImpl(private val api: APIService) : APIDataSource {

    private val _downloadedCountries = MutableLiveData<List<Country>>()
    private val _downloadedTop10Cities = MutableLiveData<List<City>>()

    override val downloadedCountries: LiveData<List<Country>> = _downloadedCountries
    override val downloadedTop10Cities: LiveData<List<City>> = _downloadedTop10Cities

    override suspend fun getCountries(): List<Country> {
        return withContext(IO) {
            _downloadedCountries.postValue(api.fetchCountries())
            api.fetchCountries()
        }
    }

    override suspend fun getTop10Cities(): List<City> {
        return withContext(IO) {
            api.fetchTop10Cities()
        }
    }
}
package com.example.exploretheworld.flow.countries

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.exploretheworld.data.models.Country
import com.example.exploretheworld.data.repositories.DataRepository

class CountriesViewModel(private val repository: DataRepository) : ViewModel() {
    val countries: LiveData<List<Country>> = liveData {
        emit(repository.fetchAllCountries())
    }
}
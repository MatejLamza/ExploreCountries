package com.example.exploretheworld.flow.cities

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.exploretheworld.data.models.City
import com.example.exploretheworld.data.repositories.DataRepository

class CitiesViewModel(private val repo: DataRepository) : ViewModel() {

    val top10Cities: LiveData<List<City>> = liveData {
        emit(repo.fetchTop10Cities())
    }
}
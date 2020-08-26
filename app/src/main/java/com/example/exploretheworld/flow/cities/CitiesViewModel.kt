package com.example.exploretheworld.flow.cities

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.exploretheworld.common.state.Done
import com.example.exploretheworld.common.state.Error
import com.example.exploretheworld.common.state.Loading
import com.example.exploretheworld.common.state.State
import com.example.exploretheworld.data.models.City
import com.example.exploretheworld.data.repositories.DataRepository
import kotlinx.coroutines.delay

class CitiesViewModel(private val repo: DataRepository) : ViewModel() {

    private var _state = MutableLiveData<State>()
    val state: LiveData<State> = _state

    val top10Cities: LiveData<List<City>> = liveData {
        try {
            _state.postValue(Loading)
            /**
             * Simulate slow connection so animation has time to show
             */
            delay(2000)
            emit(repo.fetchTop10Cities())
            _state.postValue(Done())
        } catch (e: Exception) {
            _state.postValue(Error(e))
        }
    }
}
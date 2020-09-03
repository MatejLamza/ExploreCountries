package com.example.exploretheworld.flow.countries

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.exploretheworld.common.state.Done
import com.example.exploretheworld.common.state.Error
import com.example.exploretheworld.common.state.Loading
import com.example.exploretheworld.common.state.State
import com.example.exploretheworld.data.models.Country
import com.example.exploretheworld.data.repositories.DataRepository
import kotlinx.coroutines.delay

class CountriesViewModel(private val repository: DataRepository) : ViewModel() {

    private var _state = MutableLiveData<State>()
    val state: LiveData<State> = _state

    /**
     * Because we previously fetched data from API and saved it to cache during splash screen, we can now
     * fetch data from cache instead of making another api call.
     */
    val countries: LiveData<List<Country>> = liveData {
        try {
            _state.value = Loading
            /**
             * Simulate longer operation to let animations be visible.
             */
            delay(500)
            emit(repository.getAllCountries())
            _state.value = Done()
        } catch (e: Exception) {
            _state.value = Error(e)
        }
    }
}
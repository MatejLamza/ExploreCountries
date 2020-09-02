package com.example.exploretheworld.flow.splash

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.exploretheworld.ExploreTheWorldApplication
import com.example.exploretheworld.common.state.Done
import com.example.exploretheworld.common.state.Error
import com.example.exploretheworld.common.state.Loading
import com.example.exploretheworld.common.state.State
import com.example.exploretheworld.data.repositories.DataRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashViewModel(
    private val dataRepository: DataRepository,
    exploreTheWorldApp: ExploreTheWorldApplication
) : AndroidViewModel(exploreTheWorldApp) {

    private var _state = MutableLiveData<State>()
    val state: LiveData<State> = _state

    /**
     * This will be triggered every time user comes to splash screen. So every time user starts application
     * new data will be downloaded and saved to database. After job is done state is changed to Done and UI handles
     * redirecting.
     */
    init {
        viewModelScope.launch {
            try {
                _state.value = Loading
                dataRepository.fetchAndSaveDataToDatabase()
                /**
                 * Let animations load properly so simulate heavy operation.
                 */
                delay(2000)
                _state.value = Done()
            } catch (e: Exception) {
                _state.value = Error(e)
            }
        }
    }
}
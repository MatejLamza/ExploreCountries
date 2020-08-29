package com.example.exploretheworld.flow.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.exploretheworld.data.repositories.UserRepository
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import kotlinx.coroutines.launch

class LoginViewModel(private val userRepo: UserRepository) : ViewModel() {

    private var _currentLoggedInUser = MutableLiveData<GoogleSignInAccount>()
    val currentLoggedInUser: LiveData<GoogleSignInAccount> = _currentLoggedInUser


    fun saveLoggedInUser(account: GoogleSignInAccount) {
        viewModelScope.launch {
            userRepo.saveUser(account)
            _currentLoggedInUser.value = account
        }
    }
}
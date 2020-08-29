package com.example.exploretheworld.data.repositories

import android.content.SharedPreferences
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext

const val LOGGED_IN_USER_KEY = "com.example.exploretheworld.data.repositories.LOGGED_IN_USER"

class UserRepository(private val sharedPrefs: SharedPreferences) {

    suspend fun saveUser(googleSignInAccount: GoogleSignInAccount) {
        withContext(IO) {
            sharedPrefs.edit().apply {
                putString(LOGGED_IN_USER_KEY, Gson().toJson(googleSignInAccount))
                apply()
            }
        }
    }

    /**
     * Nullable because shared preferences can be empty
     */
    suspend fun getUser(): GoogleSignInAccount? {
        return withContext(IO) {
            if (sharedPrefs.contains(LOGGED_IN_USER_KEY)) {
                Gson().fromJson(
                    sharedPrefs.getString(LOGGED_IN_USER_KEY, "DefaultAccount"),
                    GoogleSignInAccount::class.java
                )
            } else {
                null
            }
        }
    }
}
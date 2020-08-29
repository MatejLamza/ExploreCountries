package com.example.exploretheworld.flow.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.exploretheworld.R
import com.example.exploretheworld.common.mvvm.BaseActivity
import com.example.exploretheworld.flow.splash.SplashActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import kotlinx.android.synthetic.main.activity_login.*
import org.koin.androidx.viewmodel.ext.android.viewModel

const val REQUEST_CODE_SIGN_IN = 333
const val KEY_ACCOUNT = "com.example.exploretheworld.flow.login.KEY_ACCOUNT"

class LoginActivity : BaseActivity() {

    private val loginViewModel: LoginViewModel by viewModel()

    private lateinit var googleSignInClient: GoogleSignInClient
    private var latestSignedInAccount: GoogleSignInAccount? = null
    private val googleSignInOptions: GoogleSignInOptions by lazy {
        GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        googleSignInClient = GoogleSignIn.getClient(this, googleSignInOptions)
        latestSignedInAccount = GoogleSignIn.getLastSignedInAccount(this)
        checkIfSignInIsNeeded()
        setupUI()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(ApiException::class.java)
            account?.let { loginViewModel.saveLoggedInUser(it) }
            val intent = Intent(this, SplashActivity::class.java)
            startActivity(intent)
        } catch (e: ApiException) {
            Log.e("aaa", "Handle sign in result:", e)
        }
    }

    private fun checkIfSignInIsNeeded() {
        if (latestSignedInAccount != null) {
            Toast.makeText(this, "User found, redirecting ...", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, SplashActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setupUI() {
        signIn.setOnClickListener {
            signIn()
        }
    }

    private fun signIn() {
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, REQUEST_CODE_SIGN_IN)
    }
}
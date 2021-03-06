package com.example.moviebrowser.presentation.ui.splash

import android.app.Activity
import android.app.ActivityOptions
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult
import androidx.lifecycle.Observer
import com.example.moviebrowser.R
import com.example.moviebrowser.presentation.MainActivity
import com.example.moviebrowser.presentation.util.ConnectivityManager
import com.example.moviebrowser.util.Constants
import com.example.moviebrowser.util.EspressoIdlingResource
import com.firebase.ui.auth.AuthMethodPickerLayout
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_splash.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {
    @Inject
    lateinit var connectivityManager: ConnectivityManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        subscribeObservers()
    }

    override fun onStart() {
        super.onStart()
        connectivityManager.registerConnectionObserver(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        connectivityManager.unregisterConnectionObserver(this)
    }

    private fun subscribeObservers() {
        connectivityManager.isNetworkAvailable.observe(this, Observer { isNetworkAvailable ->
            if(isNetworkAvailable) {
                noInternetText.visibility = INVISIBLE
                launchSignInFlow()
            } else {
                noInternetText.visibility = VISIBLE
            }
        })
    }

    private fun launchSignInFlow() {
        EspressoIdlingResource.increment()

        CoroutineScope(Main).launch {
            delay(1000L)
            startIntentForResult()
            EspressoIdlingResource.decrement()
        }
    }

    private fun startIntentForResult() {
        val intent = createIntent()
        startForResult.launch(intent)
    }

    private fun createIntent(): Intent {
        val providers = arrayListOf(
            AuthUI.IdpConfig.EmailBuilder().build(),
            AuthUI.IdpConfig.GoogleBuilder().build())

        val authPickerLayout = AuthMethodPickerLayout
            .Builder(R.layout.layout_login)
            .setGoogleButtonId(R.id.google_button)
            .setEmailButtonId(R.id.email_button)
            .build()

        val animationOptions = ActivityOptions.makeCustomAnimation(
            this@SplashActivity,
            android.R.anim.slide_in_left,
            android.R.anim.slide_out_right)

        return AuthUI.getInstance()
            .createSignInIntentBuilder()
            .setAvailableProviders(providers)
            .setIsSmartLockEnabled(false)
            .setTheme(R.style.LoginTheme)
            .setAuthMethodPickerLayout(authPickerLayout)
            .build().apply {
                putExtras(animationOptions.toBundle())
            }
    }

    private val startForResult = registerForActivityResult(StartActivityForResult()) { result ->
        onActivityResult(Constants.SPLASH_RETURN_CODE, result)
    }

    private fun onActivityResult(requestCode: Int, result: ActivityResult) {
        if (requestCode == Constants.SPLASH_RETURN_CODE) {
            val response = IdpResponse.fromResultIntent(result.data)
            if (result.resultCode == Activity.RESULT_OK) {
                Timber.d("Successfully signed in user ${FirebaseAuth.getInstance().currentUser?.displayName}!")
                startMainActivity()
                finishSplashActivity()
            } else {
                Timber.d("Sign in unsuccessful ${response?.error?.errorCode}")
            }
        }
    }

    private fun startMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    private fun finishSplashActivity() {
        finishActivity(Constants.SPLASH_RETURN_CODE)
        finish()
    }
}
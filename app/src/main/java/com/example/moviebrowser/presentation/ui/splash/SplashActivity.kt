package com.example.moviebrowser.presentation.ui.splash

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import com.example.moviebrowser.R
import com.example.moviebrowser.presentation.BaseActivity
import com.example.moviebrowser.presentation.MainActivity
import com.example.moviebrowser.presentation.ui.login.LoginActivity
import com.example.moviebrowser.presentation.util.FirebaseViewModel
import com.example.moviebrowser.util.EspressoIdlingResource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class SplashActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        subscribeObservers()
    }

    private fun subscribeObservers() {
        EspressoIdlingResource.increment()
        firebaseViewModel.authenticationState.observe(this, Observer { authenticationState ->
            when (authenticationState) {
                FirebaseViewModel.AuthenticationState.UNAUTHENTICATED -> {
                    startActivity(LoginActivity::class.java)
                } else -> {
                    startActivity(MainActivity::class.java)
                }
            }
        })
    }

    private fun startActivity(cls: Class<*>?) {
        CoroutineScope(Dispatchers.Main).launch {
            delay(2000L)
            startActivity(Intent(applicationContext, cls))
            EspressoIdlingResource.decrement()
            finish()
        }
    }
}
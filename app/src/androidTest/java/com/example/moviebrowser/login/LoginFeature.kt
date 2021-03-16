package com.example.moviebrowser.login

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.IdlingRegistry
import com.example.moviebrowser.R
import com.example.moviebrowser.presentation.ui.splash.SplashActivity
import com.example.moviebrowser.util.EspressoIdlingResource
import com.example.moviebrowser.util.EspressoIdlingResourceRule
import com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertDisplayed
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class LoginFeature {

    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val espressoIdlingResoureRule = EspressoIdlingResourceRule()

    @Before
    fun init() {
        hiltRule.inject()
        ActivityScenario.launch(SplashActivity::class.java)
    }

    @Test
    fun displayLoginScreen() {
        assertDisplayed(R.id.layoutLogin)
        assertDisplayed(R.id.splash_image)
        assertDisplayed(R.id.google_button)
        assertDisplayed(R.id.email_button)
    }
}
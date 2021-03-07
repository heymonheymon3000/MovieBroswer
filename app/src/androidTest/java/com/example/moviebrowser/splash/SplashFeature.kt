package com.example.moviebrowser.splash

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.IdlingRegistry
import com.example.moviebrowser.R
import com.example.moviebrowser.presentation.ui.splash.SplashActivity
import com.example.moviebrowser.util.EspressoIdlingResource
import com.example.moviebrowser.util.EspressoIdlingResourceRule
import com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertDisplayed
import com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertNotDisplayed
import com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertNotExist
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class SplashFeature {

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
    fun displaySplashActivity() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.countingIdlingResource)
        assertDisplayed(R.id.splashLayout)
    }

    @Test
    fun hideSplashScreen() {
        assertNotExist(R.id.splashLayout)
    }
}
package com.example.moviebrowser.movie

import android.view.View
import android.view.ViewGroup
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import com.example.moviebrowser.R
import com.example.moviebrowser.presentation.MainActivity
import com.example.moviebrowser.util.EspressoIdlingResourceRule
import com.schibsted.spain.barista.assertion.BaristaRecyclerViewAssertions.assertRecyclerViewItemCount
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class MovieListFeature {

    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val espressoIdlingResoureRule = EspressoIdlingResourceRule()

    @Before
    fun init() {
        hiltRule.inject()
        ActivityScenario.launch(MainActivity::class.java)
    }

    @Test
    fun displaysListOfMovies() {
        assertRecyclerViewItemCount(R.id.movie_list, 20)

//        onView(allOf(withId(R.id.movie_title), isDescendantOfA(nthChildOf(withId(R.id.movie_list), 0))))
//                .check(matches(isDisplayed()))
    }

    fun nthChildOf(parentMatcher: Matcher<View>, childPosition: Int): Matcher<View> {
        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("position $childPosition of parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                if (view.parent !is ViewGroup) return false
                val parent = view.parent as ViewGroup

                return (parentMatcher.matches(parent)
                        && parent.childCount > childPosition
                        && parent.getChildAt(childPosition) == view)
            }
        }
    }
}

//        val scenario = launchFragmentInHiltContainer<MovieListFragment>(
//                factory = fragmentFactory
//        )
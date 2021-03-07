package com.example.moviebrowser.movie

import com.example.moviebrowser.fragments.movie.MovieFragmentFactory
import com.example.moviebrowser.util.launchFragmentInHiltContainer
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import javax.inject.Inject

@HiltAndroidTest
class MovieListFeature {

    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var fragmentFactory: MovieFragmentFactory

    @Before
    fun init() {
        hiltRule.inject()
//        val scenario = launchFragmentInHiltContainer<MovieListFragment>(
//                factory = fragmentFactory
//        )
    }


}
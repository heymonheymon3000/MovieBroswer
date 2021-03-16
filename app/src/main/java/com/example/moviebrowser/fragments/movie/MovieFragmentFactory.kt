package com.example.moviebrowser.fragments.movie

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.example.moviebrowser.presentation.ui.movie.MovieListFragment
import javax.inject.Inject

class MovieFragmentFactory: FragmentFactory() {
    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when(className) {
            MovieListFragment::class.java.name -> {
                MovieListFragment()
            }
            else -> {
                super.instantiate(classLoader, className)
            }
        }
    }
}
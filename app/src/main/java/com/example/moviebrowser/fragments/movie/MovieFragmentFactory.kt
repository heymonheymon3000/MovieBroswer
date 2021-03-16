package com.example.moviebrowser.fragments.movie

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.bumptech.glide.request.RequestOptions
import com.example.moviebrowser.presentation.ui.movie.MovieListFragment
import javax.inject.Inject

class MovieFragmentFactory
    @Inject
    constructor(private val requestOptions: RequestOptions): FragmentFactory() {
    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when(className) {
            MovieListFragment::class.java.name -> {
                MovieListFragment(requestOptions)
            }
            else -> {
                super.instantiate(classLoader, className)
            }
        }
    }
}
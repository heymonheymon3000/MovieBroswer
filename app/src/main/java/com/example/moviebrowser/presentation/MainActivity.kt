package com.example.moviebrowser.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.moviebrowser.R
import com.example.moviebrowser.fragments.movie.MovieFragmentFactory
import com.example.moviebrowser.presentation.ui.movie.MovieListFragment
import com.firebase.ui.auth.AuthUI
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

class MainActivity : BaseActivity() {

    @Inject
    lateinit var fragmentFactory: MovieFragmentFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.fragmentFactory = fragmentFactory
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_fragment_container, MovieListFragment::class.java, null)
            .commit()
    }

    override fun onDestroy() {
        super.onDestroy()
        AuthUI.getInstance().signOut(applicationContext)
    }
}
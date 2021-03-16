package com.example.moviebrowser.presentation

import android.os.Bundle
import com.example.moviebrowser.R
import com.example.moviebrowser.fragments.movie.MovieFragmentFactory
import com.example.moviebrowser.presentation.ui.movie.MovieListFragment
import com.firebase.ui.auth.AuthUI
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity: BaseActivity() {

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

    override fun displayProgressBar(isLoading: Boolean) {
//        if(bool){
//            progress_bar.visibility = View.VISIBLE
//        }
//        else{
//            progress_bar.visibility = View.GONE
//        }
    }

    override fun expandAppBar() {
//        setSupportActionBar(tool_bar)
    }
}
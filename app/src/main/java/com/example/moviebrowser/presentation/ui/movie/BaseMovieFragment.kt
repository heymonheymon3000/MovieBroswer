package com.example.moviebrowser.presentation.ui.movie

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.moviebrowser.presentation.ui.UICommunicationListener
import com.example.moviebrowser.presentation.ui.movie.viewmodel.MovieViewModel
import timber.log.Timber

abstract class BaseMovieFragment
constructor(
    @LayoutRes
    private val layoutRes: Int
): Fragment(layoutRes) {


    lateinit var uiCommunicationListener: UICommunicationListener

    val viewModel: MovieViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        setupActionBarWithNavController(R.id.blogFragment, activity as AppCompatActivity)
//        setupChannel()
    }

//    private fun setupChannel() = viewModel.setupChannel()

    fun setupActionBarWithNavController(fragmentId: Int, activity: AppCompatActivity){
//        val appBarConfiguration = AppBarConfiguration(setOf(fragmentId))
//        NavigationUI.setupActionBarWithNavController(
//            activity,
//            findNavController(),
//            appBarConfiguration
//        )
    }

//    override fun onAttach(context: Context) {
//        super.onAttach(context)
//        try{
//            uiCommunicationListener = context as UICommunicationListener
//        } catch(e: ClassCastException){
//            Timber.e("$context must implement UICommunicationListener" )
//        }
//    }
}
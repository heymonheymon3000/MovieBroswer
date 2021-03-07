package com.example.moviebrowser.presentation

import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.moviebrowser.presentation.util.ConnectivityManager
import com.example.moviebrowser.presentation.util.FirebaseViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
abstract class BaseActivity: AppCompatActivity() {
    @Inject
    lateinit var connectivityManager: ConnectivityManager

    val firebaseViewModel: FirebaseViewModel by viewModels()


}
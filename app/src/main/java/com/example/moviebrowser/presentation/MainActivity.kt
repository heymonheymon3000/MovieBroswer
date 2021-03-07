package com.example.moviebrowser.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.moviebrowser.R
import com.firebase.ui.auth.AuthUI
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onDestroy() {
        super.onDestroy()
        AuthUI.getInstance().signOut(applicationContext)
    }
}
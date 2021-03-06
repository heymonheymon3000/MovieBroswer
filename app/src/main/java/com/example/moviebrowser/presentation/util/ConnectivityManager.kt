package com.example.moviebrowser.presentation.util

import android.app.Application
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ConnectivityManager
@Inject
constructor(application: Application) {
    private val connectionLiveData = ConnectionLiveData(application)

    private val _isNetworkAvailable: MutableLiveData<Boolean> = MutableLiveData(false)
    val isNetworkAvailable: LiveData<Boolean>
        get() = _isNetworkAvailable

    fun registerConnectionObserver(lifecycleOwner: LifecycleOwner){
        connectionLiveData.observe(lifecycleOwner, { isConnected ->
            isConnected?.let { _isNetworkAvailable.value = it }
        })
    }

    fun unregisterConnectionObserver(lifecycleOwner: LifecycleOwner){
        connectionLiveData.removeObservers(lifecycleOwner)
    }
}
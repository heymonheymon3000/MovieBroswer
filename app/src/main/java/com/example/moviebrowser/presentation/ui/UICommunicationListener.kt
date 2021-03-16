package com.example.moviebrowser.presentation.ui

import com.example.moviebrowser.presentation.util.Response
import com.example.moviebrowser.presentation.util.StateMessageCallback


interface UICommunicationListener {

    fun onResponseReceived(
        response: Response,
        stateMessageCallback: StateMessageCallback
    )

    fun displayProgressBar(isLoading: Boolean)

    fun expandAppBar()

    fun hideSoftKeyboard()

    fun isStoragePermissionGranted(): Boolean
}

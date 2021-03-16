package com.example.moviebrowser.presentation.ui.movie.state

import com.example.moviebrowser.presentation.util.StateEvent

sealed class  MovieStateEvent: StateEvent {
    class MovieSearchEvent(
            val movieType: String = ""
    ): MovieStateEvent() {
        override fun errorInfo(): String {
            return "Error searching for movies."
        }

        override fun toString(): String {
            return "MovieStateEvent"
        }
    }
}
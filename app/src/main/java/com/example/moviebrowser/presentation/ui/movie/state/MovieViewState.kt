package com.example.moviebrowser.presentation.ui.movie.state

import android.os.Parcelable
import com.example.moviebrowser.domain.model.Movie
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieViewState(
    // BlogFragment vars
    var movieFields: MovieFields = MovieFields()
): Parcelable {
    @Parcelize
    data class MovieFields(
        var movieList: List<Movie> = ArrayList<Movie>(),
        var searchQuery: String = "",
        var page: Int = 1,
        var isQueryInProgress: Boolean = false,
        var isQueryExhausted: Boolean = false,
        var layoutManagerState: Parcelable? = null
    ): Parcelable
}

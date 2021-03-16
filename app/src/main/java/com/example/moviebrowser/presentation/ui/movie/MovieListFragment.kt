package com.example.moviebrowser.presentation.ui.movie

import android.os.Bundle
import android.view.View
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.moviebrowser.R
import com.example.moviebrowser.domain.model.Movie
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieListFragment: BaseMovieFragment(R.layout.fragment_movie_list),
    MovieListAdaptor.Interaction,
    SwipeRefreshLayout.OnRefreshListener {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        if(requestOptions == null) {
//            Timber.i("requestOptions is NULL")
//        } else {
//            Timber.i("requestOptions is NOT NULL")
//        }
    }

    override fun onItemSelected(position: Int, item: Movie) {
        TODO("Not yet implemented")
    }

    override fun restoreListPosition() {
        TODO("Not yet implemented")
    }

    override fun onRefresh() {
        TODO("Not yet implemented")
    }
}
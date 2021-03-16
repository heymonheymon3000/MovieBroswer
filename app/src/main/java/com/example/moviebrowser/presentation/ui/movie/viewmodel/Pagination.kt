package com.example.moviebrowser.presentation.ui.movie.viewmodel

import com.example.moviebrowser.presentation.ui.movie.state.MovieStateEvent.MovieSearchEvent
import com.example.moviebrowser.presentation.ui.movie.state.MovieViewState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import timber.log.Timber


@FlowPreview
@UseExperimental(ExperimentalCoroutinesApi::class)
fun MovieViewModel.resetPage(){
    val update = getCurrentViewStateOrNew()
    update.movieFields.page = 1
    setViewState(update)
}

@FlowPreview
@UseExperimental(ExperimentalCoroutinesApi::class)
fun MovieViewModel.refreshFromCache(){
    if(!isJobAlreadyActive(MovieSearchEvent())){
        setQueryExhausted(false)
        setStateEvent(MovieSearchEvent("false"))
    }
}

@FlowPreview
@UseExperimental(ExperimentalCoroutinesApi::class)
fun MovieViewModel.loadFirstPage() {
    if(!isJobAlreadyActive(MovieSearchEvent())){
        setQueryExhausted(false)
        resetPage()
        setStateEvent(MovieSearchEvent())
        Timber.e("BlogViewModel: loadFirstPage: ${viewState.value!!.movieFields.searchQuery}")
    }
}

@FlowPreview
@UseExperimental(ExperimentalCoroutinesApi::class)
private fun MovieViewModel.incrementPageNumber(){
    val update = getCurrentViewStateOrNew()
    val page = update.copy().movieFields.page ?: 1
    update.movieFields.page = page.plus(1)
    setViewState(update)
}

@FlowPreview
@UseExperimental(ExperimentalCoroutinesApi::class)
fun MovieViewModel.nextPage(){
    if(!isJobAlreadyActive(MovieSearchEvent())
            && !viewState.value!!.movieFields.isQueryExhausted!!
    ){
        Timber.d("BlogViewModel: Attempting to load next page...")
        incrementPageNumber()
        setStateEvent(MovieSearchEvent())
    }
}

@FlowPreview
@UseExperimental(ExperimentalCoroutinesApi::class)
fun MovieViewModel.handleIncomingMovieListData(viewState: MovieViewState){
    viewState.movieFields.let { movieFields ->
        movieFields.movieList?.let { setMovieListData(it) }
    }
}
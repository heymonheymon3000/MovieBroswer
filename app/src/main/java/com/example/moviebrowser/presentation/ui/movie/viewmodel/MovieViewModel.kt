package com.example.moviebrowser.presentation.ui.movie.viewmodel

import com.example.moviebrowser.presentation.ui.BaseViewModel
import com.example.moviebrowser.presentation.ui.movie.state.MovieStateEvent.MovieSearchEvent
import com.example.moviebrowser.presentation.ui.movie.state.MovieViewState
import com.example.moviebrowser.presentation.util.*
import com.example.moviebrowser.presentation.util.ErrorHandling.Companion.INVALID_STATE_EVENT
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MovieViewModel :
        BaseViewModel<MovieViewState>()
{
    @FlowPreview
    override fun handleNewData(data: MovieViewState) {
        data.movieFields.let { movieFields ->

            handleIncomingMovieListData(data)

            setQueryExhausted(movieFields.isQueryExhausted)
        }
    }

    override fun setStateEvent(stateEvent: StateEvent) {
        if(!isJobAlreadyActive(stateEvent)){


                val job: Flow<DataState<MovieViewState>> = when(stateEvent){

                    is MovieSearchEvent -> {
////                        if(stateEvent.clearLayoutManagerState){
////                            clearLayoutManagerState()
////                        }
//                        blogRepository.searchBlogPosts(
//                                stateEvent = stateEvent,
//                                authToken = authToken,
//                                query = getSearchQuery(),
//                                filterAndOrder = getOrder() + getFilter(),
//                                page = getPage()
//                        )
                        flow {
                            emit(
                                    DataState.error<MovieViewState>(
                                            response = Response(
                                                    message = INVALID_STATE_EVENT,
                                                    uiComponentType = UIComponentType.None(),
                                                    messageType = MessageType.Error()
                                            ),
                                            stateEvent = stateEvent
                                    )
                            )
                        }
                    }


                    else -> {
                        flow {
                            emit(
                                DataState.error<MovieViewState>(
                                    response = Response(
                                            message = INVALID_STATE_EVENT,
                                            uiComponentType = UIComponentType.None(),
                                            messageType = MessageType.Error()
                                    ),
                                    stateEvent = stateEvent
                                )
                            )
                        }
                    }
                }

                // listens for emits.  The emit will not start pouring in until onEach is called on the flow
                launchJob(stateEvent, job)

        }
    }

    override fun initNewViewState(): MovieViewState {
        return MovieViewState()
    }

    override fun onCleared() {
        super.onCleared()
        cancelActiveJobs()
    }
}
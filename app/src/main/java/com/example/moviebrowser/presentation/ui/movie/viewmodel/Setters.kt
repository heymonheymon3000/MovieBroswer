package com.example.moviebrowser.presentation.ui.movie.viewmodel

import android.os.Parcelable
import com.example.moviebrowser.domain.model.Movie
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

@FlowPreview
@UseExperimental(ExperimentalCoroutinesApi::class)
fun MovieViewModel.setQuery(query: String){
    val update = getCurrentViewStateOrNew()
    update.movieFields.searchQuery = query
    setViewState(update)
}

@FlowPreview
@UseExperimental(ExperimentalCoroutinesApi::class)
fun MovieViewModel.setMovieListData(blogList: List<Movie>){
    val update = getCurrentViewStateOrNew()
    update.movieFields.movieList = blogList
    setViewState(update)
}



@FlowPreview
@UseExperimental(ExperimentalCoroutinesApi::class)
fun MovieViewModel.setQueryExhausted(isExhausted: Boolean){
    val update = getCurrentViewStateOrNew()
    update.movieFields.isQueryExhausted = isExhausted
    setViewState(update)
}


//// Filter can be "date_updated" or "username"
//@FlowPreview
//@UseExperimental(ExperimentalCoroutinesApi::class)
//fun BlogViewModel.setBlogFilter(filter: String?){
//    filter?.let{
//        val update = getCurrentViewStateOrNew()
//        update.blogFields.filter = filter
//        setViewState(update)
//    }
//}
//
//// Order can be "-" or ""
//// Note: "-" = DESC, "" = ASC
//@FlowPreview
//@UseExperimental(ExperimentalCoroutinesApi::class)
//fun BlogViewModel.setBlogOrder(order: String){
//    val update = getCurrentViewStateOrNew()
//    update.blogFields.order = order
//    setViewState(update)
//}

@FlowPreview
@UseExperimental(ExperimentalCoroutinesApi::class)
fun MovieViewModel.setLayoutManagerState(layoutManagerState: Parcelable){
    val update = getCurrentViewStateOrNew()
    update.movieFields.layoutManagerState = layoutManagerState
    setViewState(update)
}

@FlowPreview
@UseExperimental(ExperimentalCoroutinesApi::class)
fun MovieViewModel.clearLayoutManagerState(){
    val update = getCurrentViewStateOrNew()
    update.movieFields.layoutManagerState = null
    setViewState(update)
}

@FlowPreview
@UseExperimental(ExperimentalCoroutinesApi::class)
fun MovieViewModel.removeDeletedBlogPost(){
    val update = getCurrentViewStateOrNew()
    val list = update.movieFields.movieList.toMutableList()
    for(i in 0 until list.size){
        if(list[i] == getMovie()){
            list.remove(getMovie())
            break
        }
    }
    setMovieListData(list)
}

@FlowPreview
@UseExperimental(ExperimentalCoroutinesApi::class)
fun MovieViewModel.updateListItem(){
    val update = getCurrentViewStateOrNew()
    val list = update.movieFields.movieList.toMutableList()
    val newBlogPost = getMovie()
    for(i in 0 until list.size){
        if(list[i].id == newBlogPost.id){
            list[i] = newBlogPost
            break
        }
    }
    update.movieFields.movieList = list
    setViewState(update)
}



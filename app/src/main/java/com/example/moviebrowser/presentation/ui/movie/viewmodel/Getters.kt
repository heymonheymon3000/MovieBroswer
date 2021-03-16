package com.example.moviebrowser.presentation.ui.movie.viewmodel

import com.example.moviebrowser.domain.model.Movie
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

@FlowPreview
@UseExperimental(ExperimentalCoroutinesApi::class)
fun MovieViewModel.getIsQueryExhausted(): Boolean {
    return getCurrentViewStateOrNew().movieFields.isQueryExhausted
            ?: false
}

//@FlowPreview
//@UseExperimental(ExperimentalCoroutinesApi::class)
//fun MovieViewModel.getFilter(): String {
//    return getCurrentViewStateOrNew().movieFields.filter
//            ?: BLOG_FILTER_DATE_UPDATED
//}

//@FlowPreview
//@UseExperimental(ExperimentalCoroutinesApi::class)
//fun BlogViewModel.getOrder(): String {
//    return getCurrentViewStateOrNew().blogFields.order
//            ?: BLOG_ORDER_DESC
//}

@FlowPreview
@UseExperimental(ExperimentalCoroutinesApi::class)
fun MovieViewModel.getSearchQuery(): String {
    return getCurrentViewStateOrNew().movieFields.searchQuery
            ?: return ""
}

@FlowPreview
@UseExperimental(ExperimentalCoroutinesApi::class)
fun MovieViewModel.getPage(): Int{
    return getCurrentViewStateOrNew().movieFields.page
            ?: return 1
}



@FlowPreview
@UseExperimental(ExperimentalCoroutinesApi::class)
fun MovieViewModel.getMovie(): Movie {
    getCurrentViewStateOrNew().let {
        return getDummyMovie()
//        return@let it.viewBlogFields.movie.let {
//            return@let it
//        } ?: getDummyMovie()
    }
}

@FlowPreview
@UseExperimental(ExperimentalCoroutinesApi::class)
fun MovieViewModel.getDummyMovie(): Movie{
    return Movie(
            -1,
            "" ,
            0.0,
            "",
            "",
            "",
            false,
            "",
            "")
}
//
//@FlowPreview
//@UseExperimental(ExperimentalCoroutinesApi::class)
//fun BlogViewModel.getDummyBlogPost(): BlogPost{
//    return BlogPost(-1, "" , "", "", "", 1, "")
//}
//
//@FlowPreview
//@UseExperimental(ExperimentalCoroutinesApi::class)
//fun BlogViewModel.getUpdatedBlogUri(): Uri? {
//    getCurrentViewStateOrNew().let {
//        it.updatedBlogFields.updatedImageUri?.let {
//            return it
//        }
//    }
//    return null
//}

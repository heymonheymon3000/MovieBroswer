package com.example.moviebrowser.presentation.ui.movie

import android.graphics.Rect
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.example.moviebrowser.R
import com.example.moviebrowser.domain.model.Movie
import com.example.moviebrowser.presentation.ui.util.calculateNoOfColumns
import com.example.moviebrowser.presentation.ui.util.dpFromPx
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_movie_list.*

@AndroidEntryPoint
class MovieListFragment
constructor(private val requestOptions: RequestOptions): BaseMovieFragment(R.layout.fragment_movie_list),
    MovieListAdaptor.Interaction,
    SwipeRefreshLayout.OnRefreshListener {

    private lateinit var recyclerAdapter: MovieListAdaptor
    private var requestManager: RequestManager? = null // can leak memory, must be nullable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Restore state after process death

//        savedInstanceState?.let { inState ->
//            Timber.d("BlogViewState: inState is NOT null")
//            (inState[BLOG_VIEW_STATE_BUNDLE_KEY] as BlogViewState?)?.let { viewState ->
//                Timber.d("BlogViewState: restoring view state: $viewState")
//                viewModel.setViewState(viewState)
//            }
//        }
    }

    /**
     * !IMPORTANT!
     * Must save ViewState b/c in event of process death the LiveData in ViewModel will be lost
     */
//    override fun onSaveInstanceState(outState: Bundle) {
//        val viewState = viewModel.viewState.value
//
//        //clear the list. Don't want to save a large list to bundle.
//        viewState?.blogFields?.blogList = ArrayList()
//
//        outState.putParcelable(
//            BLOG_VIEW_STATE_BUNDLE_KEY,
//            viewState
//        )
//        super.onSaveInstanceState(outState)
//    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.setDisplayShowTitleEnabled(false)
        setHasOptionsMenu(true)
//        swipe_refresh.setOnRefreshListener(this)
        setupGlide()
        initRecyclerView()
        subscribeObservers()
    }

    private fun setupGlide(){
        val requestOptions = RequestOptions
            .placeholderOf(R.drawable.default_image)
            .error(R.drawable.default_image)

        activity?.let {
            requestManager = Glide.with(it)
                .applyDefaultRequestOptions(requestOptions)
        }
    }

    private fun initRecyclerView(){
        movie_list.apply {
            val spanCount = calculateNoOfColumns(
                    requireContext(),
                    dpFromPx(requireContext(), resources.getDimension(R.dimen.default_poster_grid_width))
            )
            layoutManager = GridLayoutManager(this@MovieListFragment.context, spanCount)
            setHasFixedSize(true)
            addItemDecoration(object : RecyclerView.ItemDecoration() {
                val spacing = resources.getDimensionPixelSize(R.dimen.default_poster_grid_spacing) / 2

                override fun getItemOffsets(
                    outRect: Rect,
                    view: View,
                    parent: RecyclerView,
                    state: RecyclerView.State
                ) {
                    outRect.set(spacing, spacing, spacing, spacing)
                }
            })

            recyclerAdapter = MovieListAdaptor(
                requestManager as RequestManager,
                this@MovieListFragment
            )
//            addOnScrollListener(object: RecyclerView.OnScrollListener(){
//                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
//                    super.onScrollStateChanged(recyclerView, newState)
//                    val layoutManager = recyclerView.layoutManager as GridLayoutManager
//                    val lastPosition = layoutManager.findLastVisibleItemPosition()
//                    if (lastPosition == recyclerAdapter.itemCount.minus(1)) {
//                        Timber.d("BlogFragment: attempting to load next page...")
//                        viewModel.nextPage()
//                    }
//                }
//            })
            adapter = recyclerAdapter
        }
    }

    private fun subscribeObservers(){

        viewModel.viewState.observe(viewLifecycleOwner, Observer{ viewState ->
            if(viewState != null){
                recyclerAdapter.apply {
                    viewState.movieFields.movieList?.let {
                        preloadGlideImages(
                            requestManager = requestManager as RequestManager,
                            list = it
                        )
                    }

                    submitList(
                            movieList = viewState.movieFields.movieList,
                            isQueryExhausted = viewState.movieFields.isQueryExhausted?: true
                    )
                }

            }
        })

//        viewModel.numActiveJobs.observe(viewLifecycleOwner, Observer { jobCounter ->
//            uiCommunicationListener.displayProgressBar(viewModel.areAnyJobsActive())
//        })
//
//        viewModel.stateMessage.observe(viewLifecycleOwner, Observer { stateMessage ->
//
//            stateMessage?.let {
//                if(isPaginationDone(stateMessage.response.message)){
//                    viewModel.setQueryExhausted(true)
//                    viewModel.clearStateMessage()
//                }else{
//                    uiCommunicationListener.onResponseReceived(
//                        response = it.response,
//                        stateMessageCallback = object: StateMessageCallback {
//                            override fun removeMessageFromStack() {
//                                viewModel.clearStateMessage()
//                            }
//                        }
//                    )
//                }
//            }
//        })
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
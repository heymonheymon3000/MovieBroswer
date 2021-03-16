package com.example.moviebrowser.presentation.ui.movie

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.*
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.example.moviebrowser.R
import com.example.moviebrowser.domain.model.Movie
import com.example.moviebrowser.util.GenericViewHolder
import kotlinx.android.synthetic.main.layout_movie_list_item.view.*
import timber.log.Timber

class MovieListAdaptor(
    private val requestManager: RequestManager,
    private val interaction: Interaction? = null
):
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val NO_MORE_RESULTS = -1
    private val MOVIE_ITEM = 0
    private val NO_MORE_RESULTS_MOVIE_MARKER = Movie(
        NO_MORE_RESULTS,
        "" ,
        0.0,
        "",
        "",
        "",
        false,
        "",
        ""
    )

    private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }
    }
    private val differ =
        AsyncListDiffer(
            MovieRecyclerChangeCallback(this),
            AsyncDifferConfig.Builder(DIFF_CALLBACK).build())


    internal inner class MovieRecyclerChangeCallback(
        private val adapter: MovieListAdaptor
    ) : ListUpdateCallback {

        override fun onChanged(position: Int, count: Int, payload: Any?) {
            adapter.notifyItemRangeChanged(position, count, payload)
        }

        override fun onInserted(position: Int, count: Int) {
            adapter.notifyItemRangeChanged(position, count)
        }

        override fun onMoved(fromPosition: Int, toPosition: Int) {
            adapter.notifyDataSetChanged()
        }

        override fun onRemoved(position: Int, count: Int) {
            adapter.notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when(viewType) {

            NO_MORE_RESULTS -> {
                Timber.e("onCreateViewHolder: No more results...")
                return GenericViewHolder(
                    LayoutInflater.from(parent.context).inflate(
                        R.layout.layout_no_more_results,
                        parent,
                        false
                    )
                )
            }

            MOVIE_ITEM -> {
                return MovieViewHolder(
                    LayoutInflater.from(parent.context).inflate(
                        R.layout.layout_movie_list_item,
                        parent,
                        false
                    ),
                    interaction = interaction,
                    requestManager = requestManager

                )
            }

            else -> {
                return MovieViewHolder(
                    LayoutInflater.from(parent.context).inflate(
                        R.layout.layout_movie_list_item,
                        parent,
                        false
                    ),
                    interaction = interaction,
                    requestManager = requestManager
                )
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        if(differ.currentList[position].id > -1){
            return MOVIE_ITEM
        }
        return differ.currentList[position].id
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is MovieViewHolder -> {
                holder.bind(differ.currentList[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    // Prepare the images that will be displayed in the RecyclerView.
    // This also ensures if the network connection is lost, they will be in the cache
    fun preloadGlideImages(
        requestManager: RequestManager,
        list: List<Movie>
    ){
        for(movie in list){
            requestManager
                .load(movie.posterPath)
                .preload()
        }
    }

    fun submitList(
        movieList: List<Movie>?,
        isQueryExhausted: Boolean
    ){
        val newList = movieList?.toMutableList()
        if (isQueryExhausted)
            newList?.add(NO_MORE_RESULTS_MOVIE_MARKER)
        val commitCallback = Runnable {
            // if process died must restore list position
            // very annoying
            interaction?.restoreListPosition()
        }
        differ.submitList(newList, commitCallback)
    }

    class MovieViewHolder
    constructor(
        itemView: View,
        val requestManager: RequestManager,
        private val interaction: Interaction?
    ) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: Movie) = with(itemView) {
            itemView.setOnClickListener {
                interaction?.onItemSelected(adapterPosition, item)
            }

            requestManager
                .load(item.posterPath)
                .transition(withCrossFade())
                .into(itemView.movie_thumbnail)
        }
    }

    interface Interaction {
        fun onItemSelected(position: Int, item: Movie)
        fun restoreListPosition()
    }
}
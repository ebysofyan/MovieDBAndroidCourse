package app.ebysofyan.itec.ui.fragments.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import app.ebysofyan.itec.R
import app.ebysofyan.itec.core.base.BaseRecyclerViewAdapter
import app.ebysofyan.itec.core.base.BaseViewHolder
import app.ebysofyan.itec.core.utils.Constants
import app.ebysofyan.itec.data.remote.model.MovieModel
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.movie_item.view.*

/**
 * Created by ebysofyan on 2020-01-25.
 */
class MainRecyclerViewAdapter : BaseRecyclerViewAdapter<MovieModel?>() {

    companion object {
        const val ITEM_VIEW_TYPE_CONTENT = 1
        const val ITEM_VIEW_TYPE_LOADING = 2
    }

    override var layoutResourceId: Int = R.layout.movie_item

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            ITEM_VIEW_TYPE_CONTENT -> ItemViewHolder(
                inflater.inflate(
                    layoutResourceId,
                    parent,
                    false
                )
            )
            else -> LoadingViewHolder(inflater.inflate(R.layout.loading_item, parent, false))
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (getItems()[position] == null) ITEM_VIEW_TYPE_LOADING
        else ITEM_VIEW_TYPE_CONTENT
    }

    override fun onBindItem(holder: BaseViewHolder, data: MovieModel?, position: Int) {
        when (holder) {
            is ItemViewHolder -> {
                val view = holder.itemView
                view.movie_item_title.text = data?.name.toString()
                view.movie_item_rating.text = data?.voteAverage.toString() ?: ""
                view.movie_item_release_date.text = data?.firstAirDate.toString()

                val imageUrl = Constants.buildImageUrl(size = "w154", fileName = data?.posterPath ?: "")
                Glide.with(view.context).load(imageUrl).into(view.movie_item_image)
            }
            else -> {
            }
        }
    }

    class ItemViewHolder(view: View) : BaseViewHolder(view)
    class LoadingViewHolder(view: View) : BaseViewHolder(view)
}
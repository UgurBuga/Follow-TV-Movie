package com.ugurbuga.followtvmovie.ui.soon.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.ugurbuga.followtvmovie.core.adapter.BaseListAdapter
import com.ugurbuga.followtvmovie.core.adapter.ListAdapterItem
import com.ugurbuga.followtvmovie.core.common.CommonUtil
import com.ugurbuga.followtvmovie.domain.poster.model.EmptyUIModel
import com.ugurbuga.followtvmovie.domain.poster.model.PosterItemUIModel
import com.ugurbuga.followtvmovie.ui.discover.adapter.EmptyViewHolder
import com.ugurbuga.followtvmovie.ui.discover.adapter.PosterHolderType

class SoonAdapter(
    private val onPosterClick: ((poster: PosterItemUIModel, imageView: AppCompatImageView) -> Unit)? = null,
) : BaseListAdapter<ListAdapterItem>(itemsSame = { old, new -> old == new },
    contentsSame = { old, new -> old == new }) {

    override fun onCreateViewHolder(
        parent: ViewGroup, inflater: LayoutInflater, viewType: Int
    ): RecyclerView.ViewHolder {


        when (viewType) {
            PosterHolderType.SOON -> return SoonViewHolder(parent, inflater)

            PosterHolderType.EMPTY -> return EmptyViewHolder(parent, inflater)

        }

        throw IllegalStateException("View Error")

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is SoonViewHolder -> {
                val item = getItem(position) as PosterItemUIModel
                holder.bind(
                    poster = item,
                    onPosterClick = onPosterClick,
                    isFirst = position == 0,
                    isLast = position == currentList.size - 1,
                )
            }

            is EmptyViewHolder -> {
                val item = getItem(position) as EmptyUIModel
                holder.bind(item)
            }

        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is PosterItemUIModel -> {
                PosterHolderType.SOON
            }
            is EmptyUIModel -> {
                PosterHolderType.EMPTY
            }
            else -> {
                CommonUtil.INVALID_INDEX
            }
        }
    }
}

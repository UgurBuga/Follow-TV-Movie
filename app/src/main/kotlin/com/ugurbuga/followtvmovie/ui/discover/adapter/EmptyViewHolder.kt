package com.ugurbuga.followtvmovie.ui.discover.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.ugurbuga.followtvmovie.bindings.executeAfter
import com.ugurbuga.followtvmovie.core.adapter.BaseViewHolder
import com.ugurbuga.followtvmovie.databinding.ItemEmptyBinding
import com.ugurbuga.followtvmovie.domain.poster.model.EmptyUIModel

class EmptyViewHolder(
    parent: ViewGroup, inflater: LayoutInflater
) : BaseViewHolder<ItemEmptyBinding>(
    binding = ItemEmptyBinding.inflate(inflater, parent, false)
) {
    fun bind(item: EmptyUIModel) {
        binding.executeAfter {
            this.item = item
        }
    }
}

package com.ugurbuga.followtvmovie.ui.discover.popularlist

import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ugurbuga.followtvmovie.R
import com.ugurbuga.followtvmovie.base.FTMBaseVMFragment
import com.ugurbuga.followtvmovie.common.Util
import com.ugurbuga.followtvmovie.databinding.FragmentPopularListBinding
import com.ugurbuga.followtvmovie.extensions.observe
import com.ugurbuga.followtvmovie.ui.discover.popularlist.adapter.PosterAdapter
import com.ugurbuga.followtvmovie.ui.discover.popularlist.adapter.PosterHolderType
import com.ugurbuga.followtvmovie.ui.discover.popularlist.adapter.PosterItemDecoration
import com.ugurbuga.followtvmovie.view.toolbar.ToolbarViewState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PopularListFragment :
    FTMBaseVMFragment<PopularListViewModel, FragmentPopularListBinding>() {

    override fun getResourceLayoutId() = R.layout.fragment_popular_list

    override fun getToolbarViewState() = ToolbarViewState.NoToolbar

    private val posterAdapter: PosterAdapter by lazy {
        PosterAdapter(
            requireContext(),
            ::onPosterItemClick
        )
    }

    companion object {
        const val ARG_POPULAR_LIST_TYPE = "arg_popular_list_type"
        fun newInstance(popularListType: String) = PopularListFragment().apply {
            arguments = Bundle().apply {
                putString(ARG_POPULAR_LIST_TYPE, popularListType)
            }
        }
    }

    override fun onInitDataBinding() {
        observe(viewModel.posterList, ::onPosterList)

        val gridLayoutManager = GridLayoutManager(context, 2)
        gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return when (posterAdapter.getItemViewType(position)) {
                    PosterHolderType.POSTER -> 1
                    PosterHolderType.LOADING -> 2
                    else -> Util.INVALID_INDEX
                }
            }
        }

        viewBinding.popularListRecyclerView.apply {
            adapter = posterAdapter
            layoutManager = gridLayoutManager
            addItemDecoration(PosterItemDecoration())

            addOnScrollListener(object :
                RecyclerView.OnScrollListener() {

                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    val layoutManager: LinearLayoutManager =
                        this@apply.layoutManager as LinearLayoutManager

                    val visibleItemCount = layoutManager.childCount
                    val totalItemCount = layoutManager.itemCount
                    val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

                    viewModel.getNewItems(
                        visibleItemCount,
                        firstVisibleItemPosition,
                        totalItemCount
                    )
                }
            })
        }

    }

    private fun onPosterList(posterList: MutableList<Any>) {
        posterAdapter.submitList(posterList.toMutableList())
    }

    private fun onPosterItemClick(id: Int) {
        //TODO: Navigate Detail
    }
}
package com.ugurbuga.followtvmovie.ui.soon.list

import androidx.lifecycle.viewModelScope
import com.ugurbuga.followtvmovie.R
import com.ugurbuga.followtvmovie.base.FTMBaseViewModel
import com.ugurbuga.followtvmovie.core.adapter.ListAdapterItem
import com.ugurbuga.followtvmovie.core.extensions.doOnSuccess
import com.ugurbuga.followtvmovie.domain.moviedetail.usecase.GetSoonMoviesUseCase
import com.ugurbuga.followtvmovie.domain.poster.model.EmptyUIModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import javax.inject.Inject

@HiltViewModel
class SoonListViewModel @Inject constructor(
    private val getSoonMoviesUseCase: GetSoonMoviesUseCase,
) : FTMBaseViewModel() {

    private val _soonListViewState = MutableStateFlow(SoonListViewState())
    val soonMovieListViewState: StateFlow<SoonListViewState>
        get() = _soonListViewState

    @Suppress("UNCHECKED_CAST")
    fun getSoonMovies() {
        getSoonMoviesUseCase(com.ugurbuga.followtvmovie.core.common.Util.EMPTY_STRING)
            .doOnSuccess {
                setList(it as ArrayList<ListAdapterItem>)
            }.launchIn(viewModelScope)
    }

    private fun setList(list: ArrayList<ListAdapterItem>) {
        if (list.isEmpty()) {
            setEmptyState()
        } else {
            _soonListViewState.value = SoonListViewState(list)
        }
    }

    private fun setEmptyState() {
        _soonListViewState.value = SoonListViewState(
            arrayListOf(
                EmptyUIModel(
                    message = R.string.empty_soon_movie_list
                )
            )
        )
    }
}
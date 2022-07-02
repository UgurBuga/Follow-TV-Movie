package com.ugurbuga.followtvmovie.ui.moviedetail.review

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.ugurbuga.followtvmovie.base.FTMBaseViewModel
import com.ugurbuga.followtvmovie.common.Argument
import com.ugurbuga.followtvmovie.core.extensions.doOnStatusChanged
import com.ugurbuga.followtvmovie.core.extensions.doOnSuccess
import com.ugurbuga.followtvmovie.domain.moviedetail.usecase.GetReviewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn

@HiltViewModel
class ReviewViewModel @Inject constructor(
    private val movieReviewUseCase: GetReviewsUseCase,
    savedStateHandle: SavedStateHandle,
) : FTMBaseViewModel() {

    private val _movieReviewViewState = MutableStateFlow(MovieReviewViewState())
    val movieReviewViewState: StateFlow<MovieReviewViewState> get() = _movieReviewViewState

    private var id: String = savedStateHandle.get<String>(Argument.ID).orEmpty()
    private var mediaType: String = savedStateHandle.get<String>(Argument.MEDIA_TYPE).orEmpty()

    init {
        getReviews()
    }

    private fun getReviews() {
        movieReviewUseCase(GetReviewsUseCase.ReviewsParams(id, mediaType))
            .doOnStatusChanged {
                initStatusState(
                    it, isShowLoading = false
                )
            }.doOnSuccess {
                _movieReviewViewState.value = MovieReviewViewState(it)
            }.launchIn(viewModelScope)
    }

}
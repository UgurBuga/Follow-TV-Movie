package com.ugurbuga.followtvmovie.ui.moviedetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.ugurbuga.followtvmovie.R
import com.ugurbuga.followtvmovie.common.FTMUtil
import com.ugurbuga.followtvmovie.core.extensions.doOnStatusChanged
import com.ugurbuga.followtvmovie.core.extensions.doOnSuccess
import com.ugurbuga.followtvmovie.domain.credit.usecase.GetCastsUseCase
import com.ugurbuga.followtvmovie.domain.external.usecase.GetExternalUrlsUseCase
import com.ugurbuga.followtvmovie.domain.favorite.usecase.AddFavoriteMovieUseCase
import com.ugurbuga.followtvmovie.domain.favorite.usecase.DeleteFavoriteUseCase
import com.ugurbuga.followtvmovie.domain.favorite.usecase.GetFavoriteUseCase
import com.ugurbuga.followtvmovie.domain.image.usecase.GetImagesUseCase
import com.ugurbuga.followtvmovie.domain.moviedetail.usecase.GetMovieDetailUseCase
import com.ugurbuga.followtvmovie.domain.moviedetail.usecase.GetRecommendationsUseCase
import com.ugurbuga.followtvmovie.domain.moviedetail.usecase.GetSimilarUseCase
import com.ugurbuga.followtvmovie.domain.moviedetail.usecase.GetVideosUseCase
import com.ugurbuga.followtvmovie.ui.discover.MediaType
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val getMovieDetailUseCase: GetMovieDetailUseCase,
    private val addFavoriteUseCase: AddFavoriteMovieUseCase,
    private val deleteFavoriteUseCase: DeleteFavoriteUseCase,
    getFavoriteUseCase: GetFavoriteUseCase,
    getVideosUseCase: GetVideosUseCase,
    getImagesUseCase: GetImagesUseCase,
    getCastsUseCase: GetCastsUseCase,
    getExternalUrlsUseCase: GetExternalUrlsUseCase,
    getRecommendationsUseCase: GetRecommendationsUseCase,
    getSimilarUseCase: GetSimilarUseCase,
    savedStateHandle: SavedStateHandle,
) : CommonViewModel(
    getFavoriteUseCase,
    getVideosUseCase,
    getImagesUseCase,
    getCastsUseCase,
    getExternalUrlsUseCase,
    getRecommendationsUseCase,
    getSimilarUseCase,
    savedStateHandle
) {

    private val _movieDetailViewState = MutableStateFlow(MovieDetailViewState())
    val movieDetailViewState: StateFlow<MovieDetailViewState> get() = _movieDetailViewState

    override fun getMediaType() = MediaType.MOVIE

    init {
        getMovieDetail()
    }

    private fun getMovieDetail() {
        getMovieDetailUseCase(GetMovieDetailUseCase.MovieDetailParams(id))
            .doOnStatusChanged {
                initStatusState(
                    it, isShowLoading = false
                )
            }.doOnSuccess {
                _movieDetailViewState.value = _movieDetailViewState.value.copy(movieDetail = it)
                isFavorite()
            }.launchIn(viewModelScope)
    }

    fun changeFavoriteState() {
        if (commonViewState.value.isFavorite) {
            movieDetailViewState.value.movieDetail?.let {
                deleteFavoriteUseCase(DeleteFavoriteUseCase.DeleteFavoriteParams(it.id)).doOnSuccess {
                    emitCommonViewEvent(CommonViewEvent.ShowSnackbar(R.string.removed_movie_list))
                }.launchIn(viewModelScope)
            }
        } else {
            val isReleased =
                FTMUtil.isReleased(movieDetailViewState.value.movieDetail?.releaseDateLong)
            if (isReleased) {
                emitCommonViewEvent(
                    CommonViewEvent.ShowWatchedOrWatchLaterDialog(
                        movieDetailViewState.value.movieDetail?.title.orEmpty()
                    )
                )

            } else {
                addFavorite(isWatched = false)
            }

        }
    }

    fun addFavorite(isWatched: Boolean) {
        val message =
            if (isWatched) R.string.added_watched_list else R.string.added_watch_later_list

        movieDetailViewState.value.movieDetail?.let {
            addFavoriteUseCase(
                AddFavoriteMovieUseCase.AddFavoriteParams(
                    MediaType.MOVIE,
                    it,
                    isWatched
                )
            )
                .doOnSuccess {
                    emitCommonViewEvent(CommonViewEvent.ShowSnackbar(message))
                }.launchIn(viewModelScope)
        }
    }
}
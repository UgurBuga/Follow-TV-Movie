package com.ugurbuga.followtvmovie.ui.moviedetail

import com.ugurbuga.followtvmovie.domain.moviedetail.model.MovieDetailUIModel

data class MovieDetailViewState(val movieDetail: MovieDetailUIModel, val isFavorite: Boolean)
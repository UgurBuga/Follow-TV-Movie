package com.ugurbuga.followtvmovie.watch.repository.movie

import com.ugurbuga.followtvmovie.watch.data.api.services.MovieService
import com.ugurbuga.followtvmovie.watch.detail.model.MovieDetailResponse
import com.ugurbuga.followtvmovie.watch.popularlist.model.MovieGeneralResponse
import com.ugurbuga.followtvmovie.watch.repository.FTMBaseRepository
import com.ugurbuga.followtvmovie.watch.util.Resource
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class MovieRepositoryImpl @Inject constructor(
    private val movieService: MovieService
) :
    MovieRepository, FTMBaseRepository() {

    override fun getPopularMovies(page: Int): Flow<Resource<MovieGeneralResponse>> {
        return onApiCall { movieService.getPopularMovies(page) }
    }

    override fun getMovieDetail(movieId: String): Flow<Resource<MovieDetailResponse>> {
        return onApiCall { movieService.getMovieDetail(movieId) }
    }

}

package com.ugurbuga.followtvmovie.watch.repository.favorites

import com.ugurbuga.followtvmovie.watch.ui.detail.PosterItemUIModel
import com.ugurbuga.followtvmovie.watch.util.Resource
import kotlinx.coroutines.flow.Flow

interface FavoritesRepository {

    fun insert(note: PosterItemUIModel): Flow<Resource<Unit>>

    fun delete(id: String): Flow<Resource<Unit>>

    fun getFavorite(mediaType: String, id: String): Flow<Resource<PosterItemUIModel?>>

    fun getSoonMovies(): Flow<Resource<MutableList<PosterItemUIModel>>>

    suspend fun update(note: PosterItemUIModel)
}

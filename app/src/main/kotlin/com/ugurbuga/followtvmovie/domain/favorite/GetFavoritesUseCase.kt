package com.ugurbuga.followtvmovie.domain.favorite

import com.ugurbuga.followtvmovie.base.FTMUseCase
import com.ugurbuga.followtvmovie.common.Resource
import com.ugurbuga.followtvmovie.domain.poster.model.PosterItemUIModel
import com.ugurbuga.followtvmovie.repository.favorites.FavoritesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFavoritesUseCase @Inject constructor(
    private val favoritesRepository: FavoritesRepository
) :
    FTMUseCase<GetFavoritesUseCase.GetFavoriteParams, MutableList<PosterItemUIModel>>() {

    data class GetFavoriteParams(val type: String)

    override fun execute(params: GetFavoriteParams): Flow<Resource<MutableList<PosterItemUIModel>>> {
        return favoritesRepository.getFavorites(params.type)
    }
}
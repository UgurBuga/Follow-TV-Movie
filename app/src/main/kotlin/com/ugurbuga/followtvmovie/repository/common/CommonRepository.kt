package com.ugurbuga.followtvmovie.repository.common

import com.ugurbuga.followtvmovie.common.ApiState
import com.ugurbuga.followtvmovie.domain.moviedetail.credit.CreditResponse
import com.ugurbuga.followtvmovie.domain.moviedetail.external.ExternalIdsResponse
import com.ugurbuga.followtvmovie.domain.moviedetail.image.ImageResponse
import com.ugurbuga.followtvmovie.domain.moviedetail.model.review.ReviewGeneralResponse
import kotlinx.coroutines.flow.Flow

interface CommonRepository {

    fun getReviews(id: String, mediaType: String): Flow<ApiState<ReviewGeneralResponse>>

    fun getExternalIds(id: String, mediaType: String): Flow<ApiState<ExternalIdsResponse>>

    fun getImages(id: String, mediaType: String): Flow<ApiState<ImageResponse>>

    fun getCredits(id: String, mediaType: String): Flow<ApiState<CreditResponse>>
}

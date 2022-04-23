package com.ugurbuga.followtvmovie.data.api.services

import com.ugurbuga.followtvmovie.domain.moviedetail.credit.CreditResponse
import com.ugurbuga.followtvmovie.domain.moviedetail.external.ExternalIdsResponse
import com.ugurbuga.followtvmovie.domain.moviedetail.image.ImageResponse
import com.ugurbuga.followtvmovie.domain.moviedetail.model.review.ReviewGeneralResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CommonService {

    @GET("{mediaType}/{id}/reviews")
    suspend fun getReviews(
        @Path("id") id: String,
        @Path("mediaType") mediaType: String,
    ): ReviewGeneralResponse

    @GET("{mediaType}/{id}/external_ids")
    suspend fun getExternalIds(
        @Path("id") id: String,
        @Path("mediaType") mediaType: String,
    ): ExternalIdsResponse

    @GET("{mediaType}/{id}/images")
    suspend fun getImages(
        @Path("id") id: String,
        @Path("mediaType") mediaType: String,
        @Query("include_image_language") includeImageLanguage: String = "null"
    ): ImageResponse

    @GET("{mediaType}/{id}/credits")
    suspend fun getCredits(
        @Path("id") id: String,
        @Path("mediaType") mediaType: String,
    ): CreditResponse
}

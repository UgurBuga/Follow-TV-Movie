package com.ugurbuga.followtvmovie.data.model.response.review


import com.squareup.moshi.Json

data class ReviewGeneralResponse(
    @Json(name = "id")
    val id: String,
    @Json(name = "page")
    val page: Int,
    @Json(name = "results")
    val results: List<ReviewResponse>,
    @Json(name = "total_pages")
    val totalPages: Int,
    @Json(name = "total_results")
    val totalResults: Int
)
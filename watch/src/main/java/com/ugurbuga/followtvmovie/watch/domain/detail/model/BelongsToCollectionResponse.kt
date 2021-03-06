package com.ugurbuga.followtvmovie.watch.domain.detail.model

import com.squareup.moshi.Json

data class BelongsToCollectionResponse(

    @Json(name = "backdrop_path")
    val backdropPath: String?,

    @Json(name = "id")
    val id: String,

    @Json(name = "name")
    val name: String,

    @Json(name = "poster_path")
    val posterPath: String?
)
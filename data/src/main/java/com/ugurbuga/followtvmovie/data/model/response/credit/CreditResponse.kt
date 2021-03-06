package com.ugurbuga.followtvmovie.data.model.response.credit


import com.squareup.moshi.Json

data class CreditResponse(
    @Json(name = "cast")
    val cast: List<CastResponse>,
    @Json(name = "crew")
    val crew: List<CrewResponse>,
    @Json(name = "id")
    val id: String
)
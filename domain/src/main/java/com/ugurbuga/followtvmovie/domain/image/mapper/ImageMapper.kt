package com.ugurbuga.followtvmovie.domain.image.mapper

import com.ugurbuga.followtvmovie.core.common.CommonUtil
import com.ugurbuga.followtvmovie.data.api.ApiConstants
import com.ugurbuga.followtvmovie.data.model.response.image.ImageResponse
import com.ugurbuga.followtvmovie.data.model.response.image.PersonImageResponse
import com.ugurbuga.followtvmovie.domain.image.model.ImageUIModel
import javax.inject.Inject

class ImageMapper @Inject constructor() {

    companion object{
        fun getPosterUrl(path: String?): String {
            return if (!path.isNullOrBlank()) {
                ApiConstants.BASE_IMAGE_URL + path
            } else {
                CommonUtil.EMPTY_STRING
            }
        }
    }

    fun toMovieImageList(response: ImageResponse): ArrayList<ImageUIModel> {
        var list = arrayListOf<ImageUIModel>()
        list.addAll(response.posters.map {
            ImageUIModel(
                ApiConstants.BASE_IMAGE_URL + it.filePath, it.aspectRatio
            )
        })

        list.addAll(response.backdrops.map {
            ImageUIModel(
                ApiConstants.BASE_IMAGE_URL + it.filePath, it.aspectRatio
            )
        })

        list.addAll(response.logos.map {
            ImageUIModel(
                ApiConstants.BASE_IMAGE_URL + it.filePath, it.aspectRatio
            )
        })

        list = ArrayList(list.filter { it.imageUrl.isNotBlank() })
        list.sortBy { it.imageUrl }
        return list
    }

    fun toPersonImageList(response: PersonImageResponse): ArrayList<ImageUIModel> {
        var list = arrayListOf<ImageUIModel>()
        list.addAll(response.profiles.map {
            ImageUIModel(
                ApiConstants.BASE_IMAGE_URL + it.filePath, it.aspectRatio
            )
        })

        list = ArrayList(list.filter { it.imageUrl.isNotBlank() })
        list.sortBy { it.imageUrl }
        return list
    }


    fun getPosterUrl(
        posterPath: String? = CommonUtil.EMPTY_STRING,
        backdropPath: String? = CommonUtil.EMPTY_STRING,
        profilePath: String? = CommonUtil.EMPTY_STRING
    ): String {

        return if (!posterPath.isNullOrBlank()) {
            ApiConstants.BASE_IMAGE_URL + posterPath

        } else if (!backdropPath.isNullOrBlank()) {
            ApiConstants.BASE_IMAGE_URL + backdropPath

        } else if (!profilePath.isNullOrBlank()) {
            ApiConstants.BASE_IMAGE_URL + profilePath

        } else {
            CommonUtil.EMPTY_STRING
        }
    }

}

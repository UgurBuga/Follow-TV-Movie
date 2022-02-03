package com.ugurbuga.followtvmovie.ui.discover.popularlist.adapter

import androidx.annotation.IntDef

@IntDef(
    PosterHolderType.POSTER,
    PosterHolderType.LOADING
)
annotation class PosterHolderType {
    companion object {
        const val POSTER = 0
        const val LOADING = 1
    }
}
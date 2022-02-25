package com.ugurbuga.followtvmovie.domain.poster.model

import com.ugurbuga.followtvmovie.base.adapter.ListAdapterItem
import com.ugurbuga.followtvmovie.common.Util

data class LoadingUIModel(val id: String = Util.EMPTY_STRING) : ListAdapterItem

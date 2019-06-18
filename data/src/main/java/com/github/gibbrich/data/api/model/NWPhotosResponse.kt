package com.github.gibbrich.data.api.model

import com.google.gson.annotations.SerializedName

data class NWPhotosResponse(
    @SerializedName("message")
    val message: List<String>? = null
)
package com.github.gibbrich.data.api.model

import com.google.gson.annotations.SerializedName

// todo - add response field
data class NWPhotosResponse(
    @SerializedName("message")
    val message: List<String>? = null
)
package com.github.gibbrich.data.api

import com.github.gibbrich.data.api.model.NWPhotosResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface Api {
    @GET("breeds/image/random/{quantity}")
    fun getPhotos(@Path("quantity") quantity: Int): Single<NWPhotosResponse>
}
package com.github.gibbrich.core.repository

import com.github.gibbrich.core.model.Breed
import io.reactivex.Single

interface DogsRepository {

    /**
     * Fetches all breed photos from cache and local db or from remote, sorted by [Breed.name]
     * @param isCacheDirty if true fetches from db and if cached data == db data, fetches from remote; otherwise tries to fetch from cache, db and remote respectively
     * @param photosQuantity amount of photos to fetched from remote
     */
    fun getRandomBreeds(
        photosQuantity: Int,
        isCacheDirty: Boolean = false
    ): Single<List<Breed>>
}
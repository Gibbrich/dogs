package com.github.gibbrich.data.repository

import com.github.gibbrich.core.model.Breed
import com.github.gibbrich.core.repository.DogsRepository
import com.github.gibbrich.data.api.Api
import com.github.gibbrich.data.converter.DogsConverter
import com.github.gibbrich.data.db.AppDatabase
import io.reactivex.Single

class DogsDataRepository(
    private val api: Api,
    private val db: AppDatabase
): DogsRepository {
    private val cachedBreeds: MutableList<Breed> = mutableListOf()

    override fun getRandomBreeds(
        photosQuantity: Int,
        isCacheDirty: Boolean
    ): Single<List<Breed>> = when {
        cachedBreeds.isNotEmpty() and isCacheDirty.not() -> {
            Single.just(cachedBreeds)
        }

        else -> {
            getAndCacheLocalBreeds(photosQuantity)
        }
    }

    private fun getAndCacheLocalBreeds(photosQuantity: Int): Single<List<Breed>> =
        db.breedDao()
            .getBreeds()
            .map { it.map(DogsConverter::fromDB) }
            .flatMap {
                val sortedCache = cachedBreeds.sortedBy(Breed::name)
                if (it.isEmpty() || it == sortedCache) {
                    getAndSaveRemoteBreeds(photosQuantity)
                } else {
                    updateCache(it)
                    Single.just(cachedBreeds)
                }
            }

    private fun getAndSaveRemoteBreeds(photosQuantity: Int): Single<List<Breed>> =
        api.getPhotos(photosQuantity)
            .map {
                DogsConverter.fromNetwork(it).sortedBy(Breed::name)
            }
            .doOnSuccess {
                updateCache(it)
                it.map(DogsConverter::toDB).also(db.breedDao()::insert)
            }
            .map { cachedBreeds }

    private fun updateCache(breeds: List<Breed>) {
        cachedBreeds.addAll(0, breeds)
    }
}
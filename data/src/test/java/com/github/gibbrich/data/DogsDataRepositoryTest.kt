package com.github.gibbrich.data

import com.github.gibbrich.core.model.Breed
import com.github.gibbrich.data.api.Api
import com.github.gibbrich.data.api.model.NWPhotosResponse
import com.github.gibbrich.data.converter.DogsConverter
import com.github.gibbrich.data.db.AppDatabase
import com.github.gibbrich.data.db.entity.DBBreed
import com.github.gibbrich.data.repository.DogsDataRepository
import io.mockk.every
import io.mockk.mockk
import io.reactivex.Single
import org.junit.Test

class DogsDataRepositoryTest {
    private val dbBreeds = listOf(
        DBBreed("Bull mastiff", "photoUrl1"),
        DBBreed("African", "photoUrl2"),
        DBBreed("Cairn", "photoUrl3")
    )

    private val dbBreeds2 = listOf(
        DBBreed("Pitbull", "photoUrl4"),
        DBBreed("Corgi", "photoUrl5"),
        DBBreed("Haski", "photoUrl6")
    )

    private val photosResponse = NWPhotosResponse(listOf(
        "https://images.dog.ceo/breeds/mastiff-bull/n02108422_3795.jpg",
        "https://images.dog.ceo/breeds/african/n02116738_3024.jpg",
        "https://images.dog.ceo/breeds/cairn/n02096177_1171.jpg"
    ))

    private val photosResponse2 = NWPhotosResponse(listOf(
        "https://images.dog.ceo/breeds/mastiff-bull/n02108422_3795.jpg",
        "https://images.dog.ceo/breeds/african/n02116738_3024.jpg",
        "https://images.dog.ceo/breeds/cairn/n02096177_1171.jpg"
    ))

    private val photosQuantity = photosResponse.message!!.size

    @Test
    fun `getRandomBreeds returns data in sorted order from Api if there is no data in cache or DB`() {
        val db = mockk<AppDatabase>(relaxed = true)
        every { db.breedDao().getBreeds() } returns Single.just(emptyList())

        val api = mockk<Api>()
        every { api.getPhotos(photosQuantity) } returns Single.just(photosResponse)

        DogsDataRepository(api, db)
            .getRandomBreeds(photosQuantity)
            .test()
            .assertValue {
                val list = DogsConverter.fromNetwork(photosResponse).sortedBy(Breed::name)
                it == list
            }
            .assertComplete()
    }

    @Test
    fun `getRandomBreeds returns data in sorted order from DB if there is no data in cache`() {
        val db = mockk<AppDatabase>(relaxed = true)
        every { db.breedDao().getBreeds() } returns Single.just(dbBreeds.sortedBy(DBBreed::name))

        val api = mockk<Api>()

        DogsDataRepository(api, db)
            .getRandomBreeds(photosQuantity)
            .test()
            .assertValue {
                val list = dbBreeds.map(DogsConverter::fromDB).sortedBy(Breed::name)
                it == list
            }
            .assertComplete()
    }

    @Test
    fun `getRandomBreeds fetches data from network if DB is empty and caches it`() {
        // fill cache with data from api
        val db = mockk<AppDatabase>(relaxed = true)
        every { db.breedDao().getBreeds() } returns Single.just(emptyList())

        val api = mockk<Api>()
        every { api.getPhotos(photosQuantity) } returns Single.just(photosResponse)

        val repository = DogsDataRepository(api, db)

        repository
            .getRandomBreeds(photosQuantity)
            .test()

        // turn off api calls
        every { api.getPhotos(photosQuantity) } returns Single.just(NWPhotosResponse(emptyList()))

        repository
            .getRandomBreeds(photosQuantity)
            .test()
            .assertValue {
                val list = DogsConverter.fromNetwork(photosResponse).sortedBy(Breed::name)
                it == list
            }
            .assertComplete()
    }

    @Test
    fun `getRandomBreeds fetches data from DB if it is not empty and caches it`() {
        // fill cache with data from db
        val db = mockk<AppDatabase>(relaxed = true)
        every { db.breedDao().getBreeds() } returns Single.just(dbBreeds.sortedBy(DBBreed::name))

        val api = mockk<Api>()
        every { api.getPhotos(photosQuantity) } returns Single.just(NWPhotosResponse(emptyList()))

        val repository = DogsDataRepository(api, db)

        repository
            .getRandomBreeds(photosQuantity)
            .test()

        // turn off db calls
        every { db.breedDao().getBreeds() } returns Single.just(emptyList())

        repository
            .getRandomBreeds(photosQuantity)
            .test()
            .assertValue {
                val list = dbBreeds.map(DogsConverter::fromDB).sortedBy(Breed::name)
                it == list
            }
            .assertComplete()
    }

    @Test
    fun `getRandomBreeds(true) clears cache, fetches data from DB if it is not empty and caches it`() {
        val accumulatedCache = mutableListOf<Breed>()
        // fill cache with data from db
        val db = mockk<AppDatabase>(relaxed = true)
        every { db.breedDao().getBreeds() } returns Single.just(dbBreeds.sortedBy(DBBreed::name))

        val api = mockk<Api>()
        every { api.getPhotos(photosQuantity) } returns Single.just(NWPhotosResponse(emptyList()))

        val repository = DogsDataRepository(api, db)

        repository
            .getRandomBreeds(photosQuantity)
            .test()

        // change data in db
        every { db.breedDao().getBreeds() } returns Single.just(dbBreeds2.sortedBy(DBBreed::name))

        // call repository.getAlbums(false) cache returns old data
        repository
            .getRandomBreeds(photosQuantity)
            .test()
            .assertValue {
                val list = dbBreeds.map(DogsConverter::fromDB).sortedBy(Breed::name)
                accumulatedCache.addAll(0, list)

                it == accumulatedCache
            }
            .assertComplete()

        // call repository.getAlbums(true), returns new data + cached data
        repository
            .getRandomBreeds(photosQuantity, true)
            .test()
            .assertValue {
                val list = dbBreeds2.map(DogsConverter::fromDB).sortedBy(Breed::name)
                accumulatedCache.addAll(0, list)

                it == accumulatedCache
            }
            .assertComplete()

        // turn off db calls
        every { db.breedDao().getBreeds() } returns Single.just(emptyList())

        // turn on api calls
        every { api.getPhotos(photosQuantity) } returns Single.just(photosResponse2)

        // call repository.getAlbums(false) cache returns new cached data
        repository
            .getRandomBreeds(photosQuantity, true)
            .test()
            .assertValue {
                val list = DogsConverter.fromNetwork(photosResponse2).sortedBy(Breed::name)
                accumulatedCache.addAll(0, list)

                it == accumulatedCache
            }
            .assertComplete()
    }
}
package com.github.gibbrich.data.converter

import com.github.gibbrich.core.model.Breed
import com.github.gibbrich.data.api.model.NWPhotosResponse
import com.github.gibbrich.data.db.entity.DBBreed
import com.github.gibbrich.data.utils.getOrDie

object DogsConverter {
    fun fromDB(data: DBBreed): Breed = Breed(data.name, data.photoUrl)

    fun toDB(data: Breed): DBBreed = DBBreed(data.name, data.photoUrl)

    fun fromNetwork(data: NWPhotosResponse): List<Breed> {
        val breeds = getOrDie(data.message, "message")
        return breeds.map(this::fromUrl)
    }

    private fun fromUrl(url: String): Breed {
        val breed = url.substringAfter("images.dog.ceo/breeds/").substringBefore("/")
        // todo - put last part in front for subbreeds
        val name = breed.replace("-", " ").capitalize()
        return Breed(
            name,
            url
        )
    }
}
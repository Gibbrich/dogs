package com.github.gibbrich.core.manager

import com.github.gibbrich.core.model.Breed

interface Navigator {
    fun switchToBreedDetailScreen(breed: Breed)
}
package com.github.gibbrich.dogslist.di

import com.github.gibbrich.data.di.DI

object DI {
    val dogsComponent: DogsComponent by lazy {
        DaggerDogsComponent
            .builder()
            .dataComponent(DI.dataComponent)
            .build()
    }
}
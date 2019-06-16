package com.github.gibbrich.data.di

import com.github.gibbrich.core.di.DI

object DI {
    val dataComponent: DataComponent by lazy {
        DaggerDataComponent
            .builder()
            .coreComponent(DI.coreComponent)
            .build()
    }
}
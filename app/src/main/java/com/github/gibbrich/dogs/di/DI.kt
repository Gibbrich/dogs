package com.github.gibbrich.dogs.di

import com.github.gibbrich.data.di.DI as DataDI

object DI {
    val appComponent: AppComponent by lazy {
        DaggerAppComponent
            .builder()
            .dataComponent(DataDI.dataComponent)
            .build()
    }
}
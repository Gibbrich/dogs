package com.github.gibbrich.dogs.di

object DI {
    val appComponent: AppComponent by lazy {
        DaggerAppComponent
            .builder()
            .build()
    }
}
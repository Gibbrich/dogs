package com.github.gibbrich.core.di

import android.app.Application

object DI {
    private lateinit var application: Application

    fun init(application: Application) {
        this.application = application
    }

    val coreComponent: CoreComponent by lazy {
        DaggerCoreComponent
            .builder()
            .coreModule(CoreModule(application))
            .build()
    }
}
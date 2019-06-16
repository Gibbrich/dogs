package com.github.gibbrich.core.di

import android.app.Application
import com.github.gibbrich.core.manager.Navigator

object DI {
    private lateinit var application: Application
    private lateinit var navigator: Navigator

    fun init(application: Application, navigator: Navigator) {
        this.application = application
        this.navigator = navigator
    }

    val coreComponent: CoreComponent by lazy {
        DaggerCoreComponent
            .builder()
            .coreModule(CoreModule(application, navigator))
            .build()
    }
}
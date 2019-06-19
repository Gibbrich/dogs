package com.github.gibbrich.dogs.di

import com.github.gibbrich.data.di.DI as DataDI

object DI {

    lateinit var appComponent: AppComponent
        private set

    fun init(appComponent: AppComponent) {
        this.appComponent = appComponent
    }
}
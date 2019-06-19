package com.github.gibbrich.dogs.di

import android.app.Application
import com.github.gibbrich.dogs.DogsApp
import com.github.gibbrich.data.di.DI as DataDI

object DI {

    private lateinit var app: Application

    fun init(app: Application) {
        this.app = app
    }

    val appComponent: AppComponent by lazy {
        (app as DogsApp).provideAppComponent()
    }
}
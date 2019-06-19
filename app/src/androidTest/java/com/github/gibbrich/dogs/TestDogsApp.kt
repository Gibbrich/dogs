package com.github.gibbrich.dogs

import android.app.Application
import com.github.gibbrich.dogs.di.AppComponent
import com.github.gibbrich.dogs.di.DI
import com.github.gibbrich.dogs.di.DaggerAppComponentMock

class TestDogsApp: Application() {

    override fun onCreate() {
        super.onCreate()

        val appcomponent: AppComponent = DaggerAppComponentMock.builder().build()
        DI.init(appcomponent)
    }
}
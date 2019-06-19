package com.github.gibbrich.dogs

import android.app.Application
import com.github.gibbrich.dogs.di.AppComponent
import com.github.gibbrich.dogs.di.DI
import com.github.gibbrich.dogs.di.DaggerAppComponent
import com.github.gibbrich.core.di.DI as CoreDI
import com.github.gibbrich.data.di.DI as DataDI

open class DogsApp: Application() {

    override fun onCreate() {
        super.onCreate()

        DI.init(this)
        CoreDI.init(this)
    }

    open fun provideAppComponent(): AppComponent = DaggerAppComponent
        .builder()
        .dataComponent(DataDI.dataComponent)
        .build()
}
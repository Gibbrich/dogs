package com.github.gibbrich.dogs

import android.app.Application
import com.github.gibbrich.dogs.di.DI
import com.github.gibbrich.dogs.manager.NavigationManager
import javax.inject.Inject
import com.github.gibbrich.core.di.DI as CoreDI

class DogsApp: Application() {

    @Inject
    internal lateinit var navigationManager: NavigationManager

    override fun onCreate() {
        super.onCreate()

        DI.appComponent.inject(this)
        CoreDI.init(this, navigationManager)
    }
}
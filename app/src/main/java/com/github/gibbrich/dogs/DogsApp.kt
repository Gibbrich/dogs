package com.github.gibbrich.dogs

import android.app.Application
import com.github.gibbrich.core.di.DI as CoreDI

class DogsApp: Application() {

    override fun onCreate() {
        super.onCreate()

        CoreDI.init(this)
    }
}
package com.github.gibbrich.dogs

import com.github.gibbrich.dogs.di.AppComponent
import com.github.gibbrich.dogs.di.DaggerAppComponentMock

class TestDogsApp: DogsApp() {
    override fun provideAppComponent(): AppComponent {
        return DaggerAppComponentMock.builder().build()
    }
}
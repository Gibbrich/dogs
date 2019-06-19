package com.github.gibbrich.dogs.di

import com.github.gibbrich.dogs.ui.activity.MainActivityTest
import dagger.Component

@AppScope
@Component(modules = [
    AppModuleMock::class
])
interface AppComponentMock: AppComponent {
    fun inject(entry: MainActivityTest)
}
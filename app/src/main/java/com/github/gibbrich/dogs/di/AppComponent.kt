package com.github.gibbrich.dogs.di

import com.github.gibbrich.dogs.DogsApp
import com.github.gibbrich.dogs.activity.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AppModule::class
])
interface AppComponent {
    fun inject(entry: DogsApp)
    fun inject(entry: MainActivity)
}
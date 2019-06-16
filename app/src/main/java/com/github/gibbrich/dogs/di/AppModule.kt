package com.github.gibbrich.dogs.di

import com.github.gibbrich.dogs.manager.NavigationManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun provideNavigator(): NavigationManager = NavigationManager()
}
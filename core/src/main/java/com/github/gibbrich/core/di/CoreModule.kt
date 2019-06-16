package com.github.gibbrich.core.di

import android.content.Context
import com.github.gibbrich.core.manager.Navigator
import dagger.Module
import dagger.Provides

@Module
class CoreModule(
    private val context: Context,
    private val navigator: Navigator
) {

    @Provides
    @CoreScope
    fun provideContext(): Context = context

    @Provides
    @CoreScope
    fun provideNavigator(): Navigator = navigator
}
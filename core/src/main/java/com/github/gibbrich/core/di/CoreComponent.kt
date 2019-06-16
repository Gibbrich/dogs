package com.github.gibbrich.core.di

import android.content.Context
import com.github.gibbrich.core.manager.Navigator
import dagger.Component

@CoreScope
@Component(modules = [
    CoreModule::class
])
interface CoreComponent {
    val context: Context
    val navigator: Navigator
}
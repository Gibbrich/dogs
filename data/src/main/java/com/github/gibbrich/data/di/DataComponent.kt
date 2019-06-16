package com.github.gibbrich.data.di

import com.github.gibbrich.core.di.CoreComponent
import com.github.gibbrich.core.manager.Navigator
import com.github.gibbrich.core.repository.DogsRepository
import com.github.gibbrich.data.di.module.ApiModule
import com.github.gibbrich.data.di.module.DBModule
import com.github.gibbrich.data.di.module.DataModule
import dagger.Component

@DataScope
@Component(
    modules = [
        ApiModule::class,
        DBModule::class,
        DataModule::class
    ],
    dependencies = [
        CoreComponent::class
    ]
)
interface DataComponent {
    val dogsRepository: DogsRepository
    val navigator: Navigator
}
package com.github.gibbrich.dogs.di

import com.github.gibbrich.data.di.DataComponent
import com.github.gibbrich.dogs.ui.viewModel.DogsViewModel
import dagger.Component

@AppScope
@Component(
    dependencies = [
        DataComponent::class
    ]
)
interface AppComponent {
    fun inject(dogsViewModel: DogsViewModel)
}
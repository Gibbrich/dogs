package com.github.gibbrich.dogslist.di

import com.github.gibbrich.data.di.DataComponent
import com.github.gibbrich.dogslist.ui.viewModel.DogsViewModel
import dagger.Component

@DogsScope
@Component(dependencies = [
    DataComponent::class
])
interface DogsComponent {
    fun inject(dogsViewModel: DogsViewModel)
}
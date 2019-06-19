package com.github.gibbrich.dogs.di

import com.github.gibbrich.core.repository.DogsRepository
import dagger.Module
import dagger.Provides
import org.mockito.Mockito

@Module
class AppModuleMock {
    @Provides
    @AppScope
    fun provideDogsRepository(): DogsRepository = Mockito.mock(DogsRepository::class.java)
}
package com.github.gibbrich.data.di.module

import com.github.gibbrich.core.repository.DogsRepository
import com.github.gibbrich.data.api.Api
import com.github.gibbrich.data.db.AppDatabase
import com.github.gibbrich.data.di.DataScope
import com.github.gibbrich.data.repository.DogsDataRepository
import dagger.Module
import dagger.Provides

@Module
class DataModule {

    @Provides
    @DataScope
    fun provideDogsRepository(
        api: Api,
        db: AppDatabase
    ): DogsRepository = DogsDataRepository(api, db)
}
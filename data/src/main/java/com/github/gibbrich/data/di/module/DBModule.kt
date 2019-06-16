package com.github.gibbrich.data.di.module

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.github.gibbrich.data.db.AppDatabase
import com.github.gibbrich.data.di.DataScope
import dagger.Module
import dagger.Provides

@Module
class DBModule {

    @Provides
    @DataScope
    fun provideDB(
        context: Context
    ): AppDatabase {
        // todo - check and remove main thread db calls
        return Room
            .databaseBuilder(context, AppDatabase::class.java, "dogs_db")
            .allowMainThreadQueries()
            .build()
    }
}
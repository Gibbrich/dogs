package com.github.gibbrich.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.github.gibbrich.data.converter.DBConverter
import com.github.gibbrich.data.db.dao.BreedDao
import com.github.gibbrich.data.db.entity.DBBreed

@Database(entities = [
    DBBreed::class
], version = 1, exportSchema = false)
@TypeConverters(DBConverter::class)
abstract class AppDatabase: RoomDatabase() {
    abstract fun breedDao(): BreedDao
}
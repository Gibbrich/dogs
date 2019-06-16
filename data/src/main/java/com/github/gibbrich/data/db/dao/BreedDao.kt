package com.github.gibbrich.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.github.gibbrich.data.db.entity.DBBreed
import io.reactivex.Single

@Dao
interface BreedDao {
    @Query("SELECT id, name, photoUrl FROM Breed ORDER BY name")
    fun getBreeds(): Single<List<DBBreed>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(albums: List<DBBreed>)
}
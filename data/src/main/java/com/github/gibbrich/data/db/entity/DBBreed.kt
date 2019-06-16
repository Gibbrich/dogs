package com.github.gibbrich.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * In general case, this table should be normalized via extracting [photoUrl]
 * in separate table. But for simplicity of this task, it's better to keep all the data together.
 */
@Entity(tableName = "Breed")
data class DBBreed(
    val name: String,
    val photoUrl: String,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
)
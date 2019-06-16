package com.github.gibbrich.data

import androidx.room.Room
import androidx.test.InstrumentationRegistry
import androidx.test.filters.SmallTest
import androidx.test.runner.AndroidJUnit4
import com.github.gibbrich.data.db.AppDatabase
import com.github.gibbrich.data.db.dao.BreedDao
import com.github.gibbrich.data.db.entity.DBBreed
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.util.*

@RunWith(AndroidJUnit4::class)
@SmallTest
class DBTest {
    private lateinit var db: AppDatabase
    private lateinit var dogDao: BreedDao

    private val dbBreeds = listOf(
        DBBreed("Bull mastiff", "https://images.dog.ceo/breeds/mastiff-bull/n02108422_3795.jpg"),
        DBBreed("African", "https://images.dog.ceo/breeds/african/n02116738_3024.jpg"),
        DBBreed("Cairn", "https://images.dog.ceo/breeds/cairn/n02096177_1171.jpg")
    )

    @Before
    fun setUp() {
        val context = InstrumentationRegistry.getTargetContext()
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        dogDao = db.breedDao()
    }

    @After
    fun closeDb() {
        db.close()
    }

    @Test
    fun getDogs_returns_empty_list_if_there_is_no_data_in_db() {
        dogDao.getBreeds()
            .test()
            .assertValue {
                it.isEmpty()
            }
    }

    @Test
    fun getDogs_returns_dogs_list_ordered_by_breed_name() {
        dogDao.insert(dbBreeds)

        dogDao
            .getBreeds()
            .test()
            .assertValue {
                it == dbBreeds.sortedBy(DBBreed::name)
            }
    }
}
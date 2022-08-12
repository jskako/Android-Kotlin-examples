package com.gamingingrs.roomwithhilt_example.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.gamingingrs.roomwithhilt_example.database.model.Animals

@Dao
interface AnimalsDao {

    @Query("SELECT * FROM animals ORDER BY id ASC")
    fun getAll(): LiveData<List<Animals>>

    @Query("SELECT * FROM animals WHERE family in(:family) ORDER BY id ASC")
    fun getAnimalBy(family: String): LiveData<List<Animals>>

    @Insert
    suspend fun insert(animal: Animals)

}
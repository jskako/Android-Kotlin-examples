package com.gamingingrs.roomwithhilt_example.data.repository

import androidx.lifecycle.LiveData
import com.gamingingrs.roomwithhilt_example.data.model.Animals

interface AnimalsRepository {

    fun getAll(): LiveData<List<Animals>>
    fun getAnimalsBy(family: String): LiveData<List<Animals>>
    suspend fun insert(animal: Animals)

}
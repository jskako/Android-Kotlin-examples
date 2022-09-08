package com.gamingingrs.roomwithhilt_example.data.repository

import androidx.lifecycle.LiveData
import com.gamingingrs.roomwithhilt_example.data.dao.AnimalsDao
import com.gamingingrs.roomwithhilt_example.data.model.Animals
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AnimalsDataSource @Inject constructor(private val animalsDao: AnimalsDao) :
    AnimalsRepository {

    override fun getAll(): LiveData<List<Animals>> = animalsDao.getAll()

    override fun getAnimalsBy(family: String): LiveData<List<Animals>> =
        animalsDao.getAnimalBy(family)

    override suspend fun insert(animal: Animals) {
        withContext(IO) {
            animalsDao.insert(animal)
        }
    }

}
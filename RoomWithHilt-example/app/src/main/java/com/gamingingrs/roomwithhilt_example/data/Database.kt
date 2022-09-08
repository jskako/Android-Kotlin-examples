package com.gamingingrs.roomwithhilt_example.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.gamingingrs.roomwithhilt_example.data.dao.AnimalsDao
import com.gamingingrs.roomwithhilt_example.data.model.Animals

@Database(entities = [Animals::class], version = 1, exportSchema = false)
abstract class Database : RoomDatabase() {
    abstract fun animalsDao(): AnimalsDao
}
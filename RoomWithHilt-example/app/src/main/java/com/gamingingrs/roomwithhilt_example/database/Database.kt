package com.gamingingrs.roomwithhilt_example.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.gamingingrs.roomwithhilt_example.database.dao.AnimalsDao

@Database(entities = [], version = 1, exportSchema = false)
abstract class Database : RoomDatabase() {
    abstract fun animalsDao(): AnimalsDao
}
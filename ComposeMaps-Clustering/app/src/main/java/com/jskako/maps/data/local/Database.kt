package com.jskako.maps.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jskako.maps.data.local.entity.BetShopEntity

@Database(
    entities = [BetShopEntity::class],
    version = 1,
    exportSchema = false
)
abstract class Database: RoomDatabase() {

    abstract val dao: BetShopDao
}
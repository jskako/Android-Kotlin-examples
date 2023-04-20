package com.jskako.maps.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Delete
import androidx.room.Query
import com.jskako.maps.data.local.entity.BetShopEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BetShopDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBetShop(betShopEntities: List<BetShopEntity>)

    @Delete
    suspend fun deleteBetShop(betShopEntity: BetShopEntity)

    @Query("""
        SELECT *
        FROM betshopentity
        ORDER BY id DESC
        """)
    fun getAll(): Flow<List<BetShopEntity>>
}
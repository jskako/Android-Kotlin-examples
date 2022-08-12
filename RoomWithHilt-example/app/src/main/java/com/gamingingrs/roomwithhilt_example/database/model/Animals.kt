package com.gamingingrs.roomwithhilt_example.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "animals")
data class Animals(val animal: String, val family: String) {

    @PrimaryKey(autoGenerate = true)
    var id: Long = 0

}

enum class AnimalFamily {
    CAT,
    DOG,
    UNKNOWN;
}
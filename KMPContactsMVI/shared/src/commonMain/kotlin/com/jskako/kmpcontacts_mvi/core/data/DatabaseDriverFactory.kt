package com.jskako.kmpcontacts_mvi.core.data

import com.squareup.sqldelight.db.SqlDriver

expect class DatabaseDriverFactory {

    fun create(): SqlDriver
}
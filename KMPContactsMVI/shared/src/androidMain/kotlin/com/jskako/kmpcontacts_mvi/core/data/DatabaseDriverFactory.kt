package com.jskako.kmpcontacts_mvi.core.data

import com.squareup.sqldelight.db.SqlDriver

import android.content.Context
import com.jskako.kmpcontacts_mvi.database.ContactDatabase
import com.squareup.sqldelight.android.AndroidSqliteDriver

actual class DatabaseDriverFactory(
    private val context: Context
) {
    actual fun create(): SqlDriver {
        return AndroidSqliteDriver(
            ContactDatabase.Schema,
            context,
            "contact.db"
        )
    }
}
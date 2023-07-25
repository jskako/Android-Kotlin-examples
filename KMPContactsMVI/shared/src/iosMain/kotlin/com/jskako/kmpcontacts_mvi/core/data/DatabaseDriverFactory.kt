package com.jskako.kmpcontacts_mvi.core.data

import com.jskako.kmpcontacts_mvi.database.ContactDatabase
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver

actual class DatabaseDriverFactory {
    actual fun create(): SqlDriver {
        return NativeSqliteDriver(ContactDatabase.Schema, "contact.db")
    }
}
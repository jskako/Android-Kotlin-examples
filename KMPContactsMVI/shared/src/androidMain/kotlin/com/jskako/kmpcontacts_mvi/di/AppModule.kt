package com.jskako.kmpcontacts_mvi.di

import com.jskako.kmpcontacts_mvi.contacts.domain.ContactDataSource
import android.content.Context
import com.jskako.kmpcontacts_mvi.contacts.data.SqlDelightContactDataSource
import com.jskako.kmpcontacts_mvi.core.data.DatabaseDriverFactory
import com.jskako.kmpcontacts_mvi.core.data.ImageStorage
import com.jskako.kmpcontacts_mvi.database.ContactDatabase

actual class AppModule(
    private val context: Context
) {

    actual val contactDataSource: ContactDataSource by lazy {
        SqlDelightContactDataSource(
            db = ContactDatabase(
                driver = DatabaseDriverFactory(context).create(),
            ),
            imageStorage = ImageStorage(context)
        )
    }
}
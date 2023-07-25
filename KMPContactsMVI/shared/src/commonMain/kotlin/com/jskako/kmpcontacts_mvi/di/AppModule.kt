package com.jskako.kmpcontacts_mvi.di

import com.jskako.kmpcontacts_mvi.contacts.domain.ContactDataSource

expect class AppModule {
    val contactDataSource: ContactDataSource
}
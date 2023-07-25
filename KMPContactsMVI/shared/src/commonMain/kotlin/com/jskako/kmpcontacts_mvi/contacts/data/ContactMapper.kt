package com.jskako.kmpcontacts_mvi.contacts.data

import com.jskako.kmpcontacts_mvi.contacts.domain.Contact
import com.jskako.kmpcontacts_mvi.core.data.ImageStorage
import database.ContactEntity

suspend fun ContactEntity.toContact(imageStorage: ImageStorage): Contact {
    return Contact(
        id = id,
        firstName = firstName,
        lastName = lastName,
        email = email,
        phoneNumber = phoneNumber,
        photoBytes = imagePath?.let { imageStorage.getImage(it) }
    )
}
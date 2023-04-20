package com.jskako.maps.domain.use_case

import android.annotation.SuppressLint
import android.location.Location
import com.google.android.gms.location.FusedLocationProviderClient
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class GetDeviceLocation {
    @SuppressLint("MissingPermission")
    suspend operator fun invoke(
        fusedLocationProviderClient: FusedLocationProviderClient
    ): Location {
        return suspendCoroutine { continuation ->
            fusedLocationProviderClient.lastLocation.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val location = task.result
                    continuation.resume(location)
                } else {
                    val exception = task.exception ?: RuntimeException("Unknown error")
                    continuation.resumeWithException(exception)
                }
            }
        }
    }
}
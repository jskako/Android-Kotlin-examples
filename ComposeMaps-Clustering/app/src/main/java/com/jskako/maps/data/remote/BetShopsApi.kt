package com.jskako.maps.data.remote

import com.jskako.maps.data.remote.dto.SearchDto
import retrofit2.http.GET
import retrofit2.http.Query

interface BetShopsApi {

    @GET("/betshops?")
    suspend fun searchBetShops(
        @Query("boundingBox") coordinates: String,
    ): SearchDto

    companion object {
        const val BASE_URL = "MY_BASE_URL"
    }
}
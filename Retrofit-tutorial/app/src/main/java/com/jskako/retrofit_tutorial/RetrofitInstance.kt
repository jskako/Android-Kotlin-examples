package com.jskako.retrofit_tutorial

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * If we use dependency injection (ex. Hilt) we do not need this class
 * This class will be provided by dependency injection module
 *
 * GsonConvertFactory is used to parse json to our data class
 */
object RetrofitInstance {

    val api: TodoApi by lazy {
        Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TodoApi::class.java)
    }
}
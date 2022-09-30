package com.jskako.retrofit_tutorial

import retrofit2.Response
import retrofit2.http.GET

interface TodoApi {

    /**
     * In get parameter goes everything after base URL
     * Base URL example: https://jsonplaceholder.typicode.com
     * Full URL example: https://jsonplaceholder.typicode.com/todos
     *
     * If you need pass parameter (ex. API key) use @Query annotation in parameter
     * Example: fun geTodos(@Query("key") key: String): Response<List<MyDataClass>>
     *
     * Use suspend to execute it in coroutine (asynchronously)
     */

    @GET("/todos")
    suspend fun geTodos(): Response<List<Todo>>

    /**
     * Example how to put data with Retrofit2
     * @Body parameter will parse JSON automatically
     *
     * Example of function:
     * @PUT("/createTodo")
     * fun createTodo(@Body todo: Todo): Response<CreateTodoResponse>
     */
}
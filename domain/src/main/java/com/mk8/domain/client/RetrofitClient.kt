package com.mk8.domain.client

import com.mk8.data.ItemWeb
import retrofit2.http.GET

interface RetrofitClient {

    companion object {
        const val BASE_URL = "https://5f07447a9c5c250016306bdd.mockapi.io/v1/webList/"
    }

    @GET("webs")
    suspend fun getWebListAsync(): List<ItemWeb>
}
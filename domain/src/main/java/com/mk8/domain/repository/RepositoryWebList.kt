package com.mk8.domain.repository

import com.mk8.data.Either
import com.mk8.data.ItemWeb
import com.mk8.data.either
import com.mk8.data.getError
import com.mk8.domain.client.RetrofitClient

class RepositoryWebList(private val retrofitClient: RetrofitClient) {

    suspend fun getWebList(): Either<String, List<ItemWeb>> {
        val data = retrofitClient.getWebListAsync()
        return either { data } getError { exception -> exception.localizedMessage }
    }
}
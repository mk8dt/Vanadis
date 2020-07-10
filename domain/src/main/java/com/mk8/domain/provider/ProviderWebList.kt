package com.mk8.domain.provider

import com.mk8.data.Either
import com.mk8.data.ItemWeb
import com.mk8.data.getValue
import com.mk8.data.value
import com.mk8.domain.cache.CacheWebList
import com.mk8.domain.repository.RepositoryWebList

class ProviderWebList(
    private val repositoryWebList: RepositoryWebList,
    private val cacheWebList: CacheWebList
) {

    suspend fun getWebList(): Either<String, List<ItemWeb>> {
        val cacheData = cacheWebList.load()

        return if (cacheData != null) value(cacheData)
        else {
            val networkData = repositoryWebList.getWebList()
            networkData.getValue { cacheWebList.save(it) }
            networkData
        }
    }
}
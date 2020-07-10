package com.mk8.domain.usecase

import com.mk8.data.Either
import com.mk8.data.ItemWeb
import com.mk8.domain.provider.ProviderWebList

class GetWebListUseCase(
    private val providerWebList: ProviderWebList
) : BaseUseCase<Either<String, List<ItemWeb>>> {

    override suspend fun execute(): Either<String, List<ItemWeb>> = providerWebList.getWebList()
}
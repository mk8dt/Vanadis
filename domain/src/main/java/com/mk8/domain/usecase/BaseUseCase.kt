package com.mk8.domain.usecase

interface BaseUseCase<T> {

    suspend fun execute(): T
}
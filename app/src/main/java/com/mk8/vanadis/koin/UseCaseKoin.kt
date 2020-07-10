package com.mk8.vanadis.koin

import com.mk8.domain.usecase.GetWebListUseCase
import org.koin.dsl.module

val useCaseModule = module {

    factory { GetWebListUseCase(get()) }
}
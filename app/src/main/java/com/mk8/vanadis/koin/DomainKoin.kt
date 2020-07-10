package com.mk8.vanadis.koin

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.mk8.domain.cache.CacheWebList
import com.mk8.domain.client.RetrofitClient
import com.mk8.domain.provider.ProviderWebList
import com.mk8.domain.repository.RepositoryWebList
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val domainModule = module {

    single { provideRetrofit() }

    single { ProviderWebList(get(), get()) }

    single { CacheWebList() }

    single { RepositoryWebList(get()) }
}

private fun provideRetrofit(): RetrofitClient = Retrofit.Builder()
    .baseUrl(RetrofitClient.BASE_URL)
    .client(OkHttpClient())
    .addConverterFactory(GsonConverterFactory.create())
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .build()
    .create(RetrofitClient::class.java)
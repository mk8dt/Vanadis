package com.mk8.vanadis

import android.app.Application
import com.mk8.vanadis.koin.domainModule
import com.mk8.vanadis.koin.useCaseModule
import com.mk8.vanadis.koin.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class VanadisApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(listOf(domainModule, useCaseModule, viewModelModule))
            androidContext(this@VanadisApp)
            androidLogger()
        }
    }
}
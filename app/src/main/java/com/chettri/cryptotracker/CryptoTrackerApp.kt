package com.chettri.cryptotracker

import android.app.Application
import com.chettri.cryptotracker.di.cryptoModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class CryptoTrackerApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@CryptoTrackerApp)
            modules(cryptoModule)
        }

    }
}
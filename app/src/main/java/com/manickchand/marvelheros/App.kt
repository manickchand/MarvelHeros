package com.manickchand.marvelheros

import android.app.Application
import com.manickchand.marvelheros.data.di.appComponent
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        configureDI()
    }

    private fun configureDI() = startKoin {
        androidContext(this@App)
        modules(appComponent)
    }
}

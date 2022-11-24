package com.dts.a20221123_romansoloviov_nycschools

import android.app.Application
import com.dts.a20221123_romansoloviov_nycschools.di.app
import com.dts.a20221123_romansoloviov_nycschools.di.viewModels
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import timber.log.Timber

class NYCSchoolsApp : Application() {

    override fun onCreate() {
        super.onCreate()
        setupDI()
        //Setup Timber for log
        Timber.plant(Timber.DebugTree())
    }

    //Setup Koin
    private fun setupDI() {
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@NYCSchoolsApp)
            modules(app, viewModels)
        }
    }
}

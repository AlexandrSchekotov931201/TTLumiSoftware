package com.apshheko.ttlumisoftware

import android.app.Application
import com.apshheko.ttlumisoftware.di.AppComponent
import com.apshheko.ttlumisoftware.di.DaggerAppComponent

class App : Application() {

    var appComponent: AppComponent? = null

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.create()
    }
}

package com.fshou.wallit

import android.app.Application
import com.fshou.core.di.databaseModule
import com.fshou.core.di.interactionModule
import com.fshou.core.di.networkModule
import com.fshou.core.di.repostoryModule
import com.fshou.wallit.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class Application : Application(){
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@Application)
            modules(
                databaseModule,
                networkModule,
                repostoryModule,
                interactionModule,
                viewModelModule
            )
        }
    }
}
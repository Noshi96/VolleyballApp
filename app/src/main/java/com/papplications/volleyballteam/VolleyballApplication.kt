package com.papplications.volleyballteam

import android.app.Application
import com.papplications.volleyballteam.app.di.drawModule
import com.papplications.volleyballteam.app.di.playerModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class VolleyballApplication : Application(){

    override fun onCreate(){
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@VolleyballApplication)
            modules(
                drawModule,
                playerModule
            )
        }

    }
}
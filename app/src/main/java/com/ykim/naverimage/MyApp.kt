package com.ykim.naverimage

import android.app.Application
import com.ykim.naverimage.di.AppComponent
import com.ykim.naverimage.di.AppModule
import com.ykim.naverimage.di.DaggerAppComponent

/**
 * Created by ykim on 2017. 7. 7..
 */
class MyApp : Application() {
    lateinit var component: AppComponent

    override fun onCreate() {
        super.onCreate()
        component = DaggerAppComponent.builder().appModule(AppModule()).build()
    }

    override fun getSystemService(name: String?): Any {
        when (name) {
            "component" -> return component
            else -> return super.getSystemService(name)
        }
    }
}
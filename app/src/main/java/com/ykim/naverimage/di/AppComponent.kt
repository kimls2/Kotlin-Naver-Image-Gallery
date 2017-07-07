package com.ykim.naverimage.di

import com.ykim.naverimage.ui.main.MainComponent
import com.ykim.naverimage.ui.main.MainModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AppModule::class))
interface AppComponent {
    fun inject(mainModule: MainModule): MainComponent
}


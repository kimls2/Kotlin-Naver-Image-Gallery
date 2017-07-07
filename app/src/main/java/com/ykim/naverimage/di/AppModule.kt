package com.ykim.naverimage.di

import com.ykim.naverimage.data.remote.NaverService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {
    @Singleton
    @Provides
    fun provideNaverService(): NaverService {
        return NaverService.Factory.makeNaverService()
    }
}

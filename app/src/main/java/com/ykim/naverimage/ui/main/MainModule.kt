package com.ykim.naverimage.ui.main

import com.ykim.naverimage.data.DataManager
import dagger.Module
import dagger.Provides

/**
 * Created by ykim on 2017. 7. 7..
 */
@Module
class MainModule {
    @Provides fun provideMainPresenter(dataManager: DataManager): MainPresenter {
        return MainPresenter(dataManager)
    }
}
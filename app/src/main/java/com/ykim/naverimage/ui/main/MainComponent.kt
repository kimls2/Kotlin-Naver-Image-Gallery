package com.ykim.naverimage.ui.main

import dagger.Subcomponent

/**
 * Created by ykim on 2017. 7. 7..
 */
@Subcomponent(modules = arrayOf(MainModule::class))
interface MainComponent {
    fun inject(mainActivity: MainActivity)
}
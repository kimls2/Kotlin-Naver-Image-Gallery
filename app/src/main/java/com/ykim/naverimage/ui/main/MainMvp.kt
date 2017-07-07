package com.ykim.naverimage.ui.main

import com.ykim.naverimage.data.model.NaverImage
import com.ykim.naverimage.ui.base.BaseMvp


/**
 * Created by ykim on 2017. 7. 6..
 */
interface MainMvp {

    interface View : BaseMvp.View {
        fun showImage(items: MutableList<NaverImage>)
    }

    interface Presenter {
        fun loadImage(searchQuery: String, start: Int)
    }
}
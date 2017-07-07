package com.ykim.naverimage.ui.detail

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.view.View
import android.view.ViewGroup
import com.ykim.naverimage.data.model.NaverImage

/**
 * Created by ykim on 2017. 7. 7..
 */
internal class DetailAdapter : PagerAdapter() {

    val naverImages: MutableList<NaverImage> = mutableListOf()
    var context: Context? = null

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        context = container.context
        val v = DetailView(container.context, naverImages[position])
        container.addView(v)
        return v

    }

    override fun isViewFromObject(view: View?, `object`: Any?): Boolean {
        return view == `object`
    }

    override fun getCount(): Int {
        return naverImages.size
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }
}
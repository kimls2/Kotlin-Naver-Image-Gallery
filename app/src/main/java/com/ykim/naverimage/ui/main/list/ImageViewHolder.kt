package com.ykim.naverimage.ui.main.list

import android.content.Context
import com.ykim.naverimage.R
import com.ykim.naverimage.data.model.NaverImage
import com.ykim.naverimage.ui.base.BaseViewHolder
import com.ykim.naverimage.util.GlideApp
import kotlinx.android.synthetic.main.item_main.view.*

/**
 * Created by ykim on 2017. 7. 7..
 */
class ImageViewHolder(context: Context) : BaseViewHolder<NaverImage>(context) {

    override fun layoutResId(): Int = R.layout.item_main

    override fun bind(item: NaverImage) {
        GlideApp.with(context).load(item.thumbnail).centerCrop().placeholder(R.drawable.placeholder).into(image_view)
        title_view.text = item.title
    }
}
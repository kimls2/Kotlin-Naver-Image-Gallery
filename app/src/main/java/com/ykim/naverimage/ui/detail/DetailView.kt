package com.ykim.naverimage.ui.detail

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.ykim.naverimage.R
import com.ykim.naverimage.data.model.NaverImage
import com.ykim.naverimage.util.GlideApp
import kotlinx.android.synthetic.main.view_detail.view.*


/**
 * Created by ykim on 2017. 7. 7..
 */
@SuppressLint("ViewConstructor")
class DetailView(context: Context, private val naverImage: NaverImage) : LinearLayout(context) {

    init {
        LayoutInflater.from(context).inflate(R.layout.view_detail, this, true)
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        naverImage.link.let {
            GlideApp.with(this)
                    .load(it)
                    .fitCenter()
                    .into(photo_view)
        }
    }
}
package com.ykim.naverimage.ui.main.list

import android.content.Context
import com.ykim.naverimage.data.model.NaverImage
import com.ykim.naverimage.ui.base.BaseListAdapter
import com.ykim.naverimage.ui.base.BaseViewHolder

/**
 * Created by ykim on 2017. 7. 7..
 */
class ImageAdapter(private val onImageSelectedListener: ((imageList: MutableList<NaverImage>, position: Int) -> Unit))
    : BaseListAdapter<NaverImage>() {
    override fun getListItemView(context: Context): BaseViewHolder<NaverImage> {
        return ImageViewHolder(context)
    }

    fun addImages(newItems: MutableList<NaverImage>) {
        val start = items.size
        items.addAll(newItems)
        notifyItemRangeInserted(start, newItems.size)
    }


    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        super.onBindViewHolder(viewHolder, position)
        viewHolder.view.setOnClickListener {
            onImageSelectedListener(items, position)
        }
    }

}
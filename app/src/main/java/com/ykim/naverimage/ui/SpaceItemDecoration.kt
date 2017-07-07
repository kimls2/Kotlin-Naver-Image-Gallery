package com.ykim.naverimage.ui

import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View

/**
 * Created by brownsoo on 2017. 7. 7..
 */

class SpaceItemDecoration(private val space: Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect?, view: View?, parent: RecyclerView?, state: RecyclerView.State?) {
        outRect!!
        outRect.left = space
        outRect.right = space
        outRect.bottom = space

        parent!!
        if (parent.getChildAdapterPosition(view) == 0)
            outRect.top = space
    }
}

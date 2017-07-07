package com.ykim.naverimage.ui.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.ykim.naverimage.R
import com.ykim.naverimage.data.model.NaverImage
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val adapter = DetailAdapter()
        adapter.naverImages.clear()
        intent.getParcelableArrayListExtra<NaverImage>(EXTRA_NIMAGE).let {
            adapter.naverImages.addAll(it)
        }

        detailViewPager.adapter = adapter
        intent.extras.getInt(EXTRA_POSITION).let {
            detailViewPager.currentItem = it
            println("intent.extras.getInt(EXTRA_POSITION) : " + intent.extras.getInt(EXTRA_POSITION))
        }


    }

    companion object {
        @JvmStatic
        fun createIntent(context: Context,
                         imageList: ArrayList<NaverImage>, position: Int): Intent {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putParcelableArrayListExtra(EXTRA_NIMAGE, imageList)
            intent.putExtra(EXTRA_POSITION, position)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
            return intent
        }

        private const val EXTRA_NIMAGE = "extra_nimage"
        private const val EXTRA_POSITION = "extra_position"
    }
}

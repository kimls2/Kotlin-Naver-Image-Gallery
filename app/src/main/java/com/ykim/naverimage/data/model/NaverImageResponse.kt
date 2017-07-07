package com.ykim.naverimage.data.model

/**
 * Created by brownsoo on 2017. 7. 6..
 */

data class NaverImageResponse(

        val lastBuildDate: String,

        val total: Int,

        val start: Int,

        val display: Int,

        val items: MutableList<NaverImage>
)
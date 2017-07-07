package com.ykim.naverimage.data.model

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by brownsoo on 2017. 7. 4..
 */

data class NaverImage(

        val title: String,

        val link: String,

        val thumbnail: String


) : Parcelable {
    companion object {
        @JvmField val CREATOR: Parcelable.Creator<NaverImage> = object : Parcelable.Creator<NaverImage> {
            override fun createFromParcel(source: Parcel): NaverImage = NaverImage(source)
            override fun newArray(size: Int): Array<NaverImage?> = arrayOfNulls(size)
        }
    }

    constructor(source: Parcel) : this(
            source.readString(),
            source.readString(),
            source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(title)
        dest.writeString(link)
        dest.writeString(thumbnail)
    }
}



package com.ykim.naverimage.util

import android.content.Context
import android.view.View
import com.ykim.naverimage.di.AppComponent

const val SIZE_PER_PAGE = 20

val SERVICE_COMPONENT = "component"

fun <T> Context.getComponent(): T {
    @Suppress("UNCHECKED_CAST")
    return this.getSystemService(SERVICE_COMPONENT) as T
}

fun Context.getAppComponent(): AppComponent {
    return this.applicationContext.getSystemService(SERVICE_COMPONENT) as AppComponent
}

fun View.setVisibility(visible: Boolean) {
    this.visibility = if (visible) View.VISIBLE else View.GONE
}
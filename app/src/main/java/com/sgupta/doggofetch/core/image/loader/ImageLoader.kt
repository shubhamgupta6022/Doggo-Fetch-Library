package com.sgupta.doggofetch.core.image.loader

import android.widget.ImageView
import androidx.annotation.DrawableRes

interface ImageLoader {
    fun into(view: ImageView)
    fun fetch(url: String): ImageLoader
    fun placeholder(@DrawableRes placeholder: Int): ImageLoader
}
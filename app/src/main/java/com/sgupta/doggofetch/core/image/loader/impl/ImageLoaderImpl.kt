package com.sgupta.doggofetch.core.image.loader.impl

import android.content.Context
import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.sgupta.doggofetch.core.image.loader.ImageLoader
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class ImageLoaderImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : ImageLoader {
    private val builder by lazy { Glide.with(context) }
    private var url: String? = null
    private var placeholder: Int? = null

    override fun into(view: ImageView) {
        builder
            .load(url)
            .apply { placeholder?.let { placeholder(it) } }
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(view)
    }

    override fun fetch(url: String): ImageLoader = apply { this.url = url }
    override fun placeholder(@DrawableRes placeholder: Int): ImageLoader = apply { this.placeholder = placeholder }
}
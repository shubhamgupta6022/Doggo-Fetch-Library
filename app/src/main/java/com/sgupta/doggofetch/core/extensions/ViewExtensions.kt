package com.sgupta.doggofetch.core.extensions

import android.view.View

fun <T : View> T.makeGone(): T = apply { visibility = View.GONE }
fun <T : View> T.makeVisible(): T = apply { visibility = View.VISIBLE }
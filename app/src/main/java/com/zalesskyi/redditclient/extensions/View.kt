package com.zalesskyi.redditclient.extensions

import android.view.View

fun View.setVisibility(isVisible: Boolean) {
    visibility = if (isVisible) View.VISIBLE else View.GONE
}

fun View.show() {
    visibility = View.VISIBLE
}

fun View.hide(isGone: Boolean = true) {
    visibility = if (isGone) View.GONE else View.INVISIBLE
}
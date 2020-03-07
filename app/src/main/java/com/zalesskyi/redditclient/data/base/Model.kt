package com.zalesskyi.redditclient.data.base

import android.os.Parcelable

interface Model<T> : Parcelable {
    var id: T?
}
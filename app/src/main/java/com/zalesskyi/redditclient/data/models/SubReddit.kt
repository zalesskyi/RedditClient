package com.zalesskyi.redditclient.data.models

import com.zalesskyi.redditclient.data.base.Model
import kotlinx.android.parcel.Parcelize

interface SubReddit : Model<Long> {

    val name: String?
    val title: String?
    val created: Long?
    val url: String?
    val authorName: String?
    val commentsNumber: Int?
    val subRedditName: String?
    val permalink: String?
    val score: Int?
}

@Parcelize
data class SubRedditModel(override var id: Long?,
                          override val name: String?,
                          override val title: String?,
                          override val created: Long?,
                          override val url: String?,
                          override val authorName: String?,
                          override val commentsNumber: Int?,
                          override val subRedditName: String?,
                          override val permalink: String?,
                          override val score: Int?) : SubReddit
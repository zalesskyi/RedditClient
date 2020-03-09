package com.zalesskyi.redditclient.data.database.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.zalesskyi.android.weatherapp.data.database.DatabaseContract

@Entity(tableName = DatabaseContract.SUBREDDITS_TABLE_NAME)
data class SubRedditDb(@PrimaryKey var id: Long,
                       var name: String?,
                       var title: String?,
                       var created: Long?,
                       var url: String?,
                       var authorName: String?,
                       var commentsNumber: Int?,
                       var subRedditName: String?,
                       var permalink: String?,
                       var score: Int?)
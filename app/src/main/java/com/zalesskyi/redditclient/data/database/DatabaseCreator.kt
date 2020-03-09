package com.zalesskyi.android.weatherapp.data.database

import android.content.Context
import androidx.room.Room
import com.zalesskyi.android.weatherapp.data.database.DatabaseContract.DATABASE_NAME
import com.zalesskyi.redditclient.data.database.RedditDatabase


object DatabaseCreator {

    lateinit var database: RedditDatabase

    fun createDb(context: Context) {
        database = Room.databaseBuilder(context, RedditDatabase::class.java, DATABASE_NAME).build()
    }
}
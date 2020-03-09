package com.zalesskyi.redditclient.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.zalesskyi.android.weatherapp.data.database.DatabaseContract.DB_VERSION
import com.zalesskyi.redditclient.data.database.dao.SubRedditsDao
import com.zalesskyi.redditclient.data.database.models.SubRedditDb

@Database(entities = [SubRedditDb::class], version = DB_VERSION)
abstract class RedditDatabase : RoomDatabase() {

    abstract fun getSubRedditsDao(): SubRedditsDao
}
package com.zalesskyi.redditclient.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.zalesskyi.android.weatherapp.data.database.DatabaseContract.SUBREDDITS_TABLE_NAME
import com.zalesskyi.redditclient.data.database.models.SubRedditDb
import io.reactivex.Single

@Dao
interface SubRedditsDao : BaseDao<SubRedditDb> {

    @Query("SELECT * FROM $SUBREDDITS_TABLE_NAME")
    fun getAll(): Single<List<SubRedditDb>>

    @Query("SELECT * FROM $SUBREDDITS_TABLE_NAME WHERE created > (SELECT created FROM $SUBREDDITS_TABLE_NAME WHERE name=:afterName)")
    fun getReddits(afterName: String): Single<List<SubRedditDb>>
}
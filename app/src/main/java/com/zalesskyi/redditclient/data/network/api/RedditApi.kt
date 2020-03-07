package com.zalesskyi.redditclient.data.network.api

import com.zalesskyi.redditclient.data.network.beans.RedditBean
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface RedditApi {

    @GET("top.json")
    fun getTopReddits(@Query("limit") limit: Int?,
                      @Query("after") after: String?): Single<RedditBean>
}
package com.zalesskyi.redditclient.data.interactors

import com.zalesskyi.redditclient.data.models.SubReddit
import com.zalesskyi.redditclient.data.network.gateways.RedditGateway
import io.reactivex.Single

interface RedditInteractor {

    fun getTopReddits(limit: Int,
                      after: String? = null): Single<List<SubReddit>>
}

class RedditInteractorImpl(private val gateway: RedditGateway) : RedditInteractor {

    override fun getTopReddits(limit: Int,
                               after: String?): Single<List<SubReddit>> =
            gateway.getTopReddits(limit, after)
}
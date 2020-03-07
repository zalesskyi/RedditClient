package com.zalesskyi.redditclient.data.network.gateways

import com.zalesskyi.redditclient.data.base.NetworkErrorUtils
import com.zalesskyi.redditclient.data.converters.bean.SubRedditBeanConverter
import com.zalesskyi.redditclient.data.converters.bean.SubRedditBeanConverterImpl
import com.zalesskyi.redditclient.data.models.SubReddit
import com.zalesskyi.redditclient.data.network.api.RedditApi
import io.reactivex.Single

interface RedditGateway {

    fun getTopReddits(limit: Int, after: String?): Single<List<SubReddit>>
}

class RedditGatewayImpl(private val api: RedditApi) : RedditGateway {

    private val converter: SubRedditBeanConverter = SubRedditBeanConverterImpl()

    override fun getTopReddits(limit: Int, after: String?): Single<List<SubReddit>> =
            api.getTopReddits(limit, after)
                    .onErrorResumeNext(NetworkErrorUtils.rxParseSingleError())
                    .map { reddit ->
                        reddit.listing?.things?.mapNotNull { it.data }
                    }
                    .compose(converter.listSingleINtoOUT())
}
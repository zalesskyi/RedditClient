package com.zalesskyi.redditclient.data.interactors

import com.zalesskyi.redditclient.data.database.repositories.SubRedditsRepository
import com.zalesskyi.redditclient.data.models.SubReddit
import com.zalesskyi.redditclient.data.network.gateways.RedditGateway
import io.reactivex.Single

interface RedditInteractor {

    /**
     * Get top reddits.
     *
     * If there is no internet connection we attempts to load it from local db.
     *
     * @param limit Page limit
     * @param after Instead of offset
     */
    fun getTopReddits(limit: Int,
                      after: String? = null): Single<List<SubReddit>>
}

class RedditInteractorImpl(private val gateway: RedditGateway,
                           private val repository: SubRedditsRepository) : RedditInteractor {

    override fun getTopReddits(limit: Int,
                               after: String?): Single<List<SubReddit>> =
            gateway.getTopReddits(limit, after)
                    .flatMap {
                        repository.save(it)
                    }
                    .onErrorResumeNext {
                        repository.run {
                            after?.let {
                                getReddits(it)
                            } ?: getAll()
                        }
                    }
}
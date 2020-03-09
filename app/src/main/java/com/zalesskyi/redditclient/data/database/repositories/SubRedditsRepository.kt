package com.zalesskyi.redditclient.data.database.repositories

import com.zalesskyi.android.weatherapp.data.database.DatabaseCreator
import com.zalesskyi.redditclient.data.converters.db.SubRedditDbConverter
import com.zalesskyi.redditclient.data.converters.db.SubRedditDbConverterImpl
import com.zalesskyi.redditclient.data.database.dao.SubRedditsDao
import com.zalesskyi.redditclient.data.models.SubReddit
import io.reactivex.Single

interface SubRedditsRepository {

    fun save(subReddits: List<SubReddit>): Single<List<SubReddit>>

    fun getReddits(after: String): Single<List<SubReddit>>

    fun getAll(): Single<List<SubReddit>>
}

class SubRedditsRepositoryImpl : SubRedditsRepository {

    private val dao: SubRedditsDao = DatabaseCreator.database.getSubRedditsDao()

    private val converter: SubRedditDbConverter = SubRedditDbConverterImpl()

    override fun save(subReddits: List<SubReddit>): Single<List<SubReddit>> =
            Single.just(subReddits)
                    .compose(converter.listSingleOUTtoIN())
                    .map { dao.insertAll(it) }
                    .map { subReddits }

    override fun getReddits(after: String): Single<List<SubReddit>> =
            dao.getReddits(after)
                    .compose(converter.listSingleINtoOUT())

    override fun getAll(): Single<List<SubReddit>> =
            dao.getAll()
                    .compose(converter.listSingleINtoOUT())
}
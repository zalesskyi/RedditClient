package com.zalesskyi.redditclient.data.converters.db

import com.zalesskyi.redditclient.data.converters.base.BaseConverter
import com.zalesskyi.redditclient.data.converters.base.Converter
import com.zalesskyi.redditclient.data.database.models.SubRedditDb
import com.zalesskyi.redditclient.data.models.SubReddit
import com.zalesskyi.redditclient.data.models.SubRedditModel

interface SubRedditDbConverter : Converter<SubRedditDb, SubReddit>

class SubRedditDbConverterImpl : BaseConverter<SubRedditDb, SubReddit>(),
        SubRedditDbConverter {

    override fun processConvertInToOut(inObject: SubRedditDb) = inObject.run {
        SubRedditModel(id, name, title, created, url,
                authorName, commentsNumber, subRedditName, permalink, score)
    }

    override fun processConvertOutToIn(outObject: SubReddit) = outObject.run {
        SubRedditDb(id ?: 0, name, title, created, url, authorName, commentsNumber, subRedditName, permalink, score)
    }
}
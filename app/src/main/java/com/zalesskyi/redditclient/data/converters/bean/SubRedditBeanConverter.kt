package com.zalesskyi.redditclient.data.converters.bean

import com.zalesskyi.redditclient.data.converters.base.BaseInConverter
import com.zalesskyi.redditclient.data.converters.base.Converter
import com.zalesskyi.redditclient.data.models.SubReddit
import com.zalesskyi.redditclient.data.models.SubRedditModel
import com.zalesskyi.redditclient.data.network.beans.SubRedditBean

interface SubRedditBeanConverter : Converter<SubRedditBean, SubReddit>

class SubRedditBeanConverterImpl : BaseInConverter<SubRedditBean, SubReddit>(),
        SubRedditBeanConverter {

    override fun processConvertInToOut(inObject: SubRedditBean) = inObject.run {
        SubRedditModel(id?.hashCode()?.toLong(), name, title, created, url,
                authorName, commentsNumber, subRedditName, permalink)
    }
}
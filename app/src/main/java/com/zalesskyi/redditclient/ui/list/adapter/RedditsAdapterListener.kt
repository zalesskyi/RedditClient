package com.zalesskyi.redditclient.ui.list.adapter

import com.zalesskyi.redditclient.data.models.SubReddit

interface RedditsAdapterListener {

    fun onItemClick(item: SubReddit)
}
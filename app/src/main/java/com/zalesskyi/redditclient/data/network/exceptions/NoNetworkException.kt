package com.zalesskyi.redditclient.data.network.exceptions

import com.zalesskyi.redditclient.R
import com.zalesskyi.redditclient.extensions.appString
import java.lang.RuntimeException

class NoNetworkException : RuntimeException() {

    override val message: String?
        get() = appString(R.string.no_network)
}
package com.zalesskyi.redditclient.data.network.beans

import com.fasterxml.jackson.annotation.JsonProperty

data class ListingBean(@JsonProperty("children")
                       val things: List<ThingBean>? = null,
                       @JsonProperty("after")
                       val after: String? = null,
                       @JsonProperty("before")
                       val before: Any? = null)
package com.zalesskyi.redditclient.data.network.beans

import com.fasterxml.jackson.annotation.JsonProperty

data class ThingBean(@JsonProperty("kind")
                     val kind: String? = null,
                     @JsonProperty("data")
                     val data: SubRedditBean? = null)
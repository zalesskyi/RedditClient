package com.zalesskyi.redditclient.data.network.beans

import com.fasterxml.jackson.annotation.JsonProperty

data class RedditBean(@JsonProperty("kind")
                      val kind: String? = null,
                      @JsonProperty("data")
                      val listing: ListingBean? = null)
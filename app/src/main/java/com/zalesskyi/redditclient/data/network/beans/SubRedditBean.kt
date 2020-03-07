package com.zalesskyi.redditclient.data.network.beans

import com.fasterxml.jackson.annotation.JsonProperty

data class SubRedditBean(@JsonProperty("id")
                         val id: String?,
                         @JsonProperty("name")
                         val name: String?,
                         @JsonProperty("title")
                         val title: String?,
                         @JsonProperty("created")
                         val created: Double?,
                         @JsonProperty("url")
                         val url: String?,
                         @JsonProperty("authorName")
                         val authorName: String?,
                         @JsonProperty("num_comments")
                         val commentsNumber: Int?,
                         @JsonProperty("subreddit")
                         val subRedditName: String?,
                         @JsonProperty("permalink")
                         val permalink: String?)
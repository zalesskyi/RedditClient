package com.zalesskyi.redditclient.di

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.zalesskyi.redditclient.BuildConfig
import com.zalesskyi.redditclient.data.network.api.RedditApi
import com.zalesskyi.redditclient.data.network.gateways.RedditGateway
import com.zalesskyi.redditclient.data.network.gateways.RedditGatewayImpl
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.jackson.JacksonConverterFactory
import java.util.concurrent.TimeUnit

@Module
class ApiModule {

    companion object {
        private const val TIMEOUT_MS = 60 * 1000L
    }

    @AppScope
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()

        if (BuildConfig.DEBUG) {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            builder.addInterceptor(logging)
        }

        builder.connectTimeout(TIMEOUT_MS, TimeUnit.MILLISECONDS)
            .readTimeout(TIMEOUT_MS, TimeUnit.MILLISECONDS)

        return builder.build()
    }

    @AppScope
    @Provides
    fun provideMapper(): ObjectMapper =
        ObjectMapper()
            .setSerializationInclusion(JsonInclude.Include.NON_NULL)
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)

    @AppScope
    @Provides
    fun provideJackson(mapper: ObjectMapper): JacksonConverterFactory =
        JacksonConverterFactory.create(mapper)

    @AppScope
    @Provides
    fun provideRestAdapter(jackson: JacksonConverterFactory, client: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(jackson)
            .baseUrl(BuildConfig.REDDIT_API_ENDPOINT)
            .client(client)
            .build()

    @AppScope
    @Provides
    fun provideRedditApi(restAdapter: Retrofit): RedditApi =
        restAdapter.create(RedditApi::class.java)

    @AppScope
    @Provides
    fun provideRedditGateway(api: RedditApi): RedditGateway =
        RedditGatewayImpl(api)
}
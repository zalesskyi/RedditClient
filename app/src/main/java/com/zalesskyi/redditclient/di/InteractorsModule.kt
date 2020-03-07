package com.zalesskyi.redditclient.di

import com.zalesskyi.redditclient.data.interactors.RedditInteractor
import com.zalesskyi.redditclient.data.interactors.RedditInteractorImpl
import com.zalesskyi.redditclient.data.network.gateways.RedditGateway
import dagger.Module
import dagger.Provides

@Module
class InteractorsModule {

    @Provides
    fun provideRedditInteractor(gateway: RedditGateway): RedditInteractor =
            RedditInteractorImpl(gateway)
}
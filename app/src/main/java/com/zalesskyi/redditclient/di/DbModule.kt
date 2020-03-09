package com.zalesskyi.redditclient.di

import com.zalesskyi.redditclient.data.database.repositories.SubRedditsRepository
import com.zalesskyi.redditclient.data.database.repositories.SubRedditsRepositoryImpl
import dagger.Module
import dagger.Provides

@Module
class DbModule {

    @AppScope
    @Provides
    fun provideRepository(): SubRedditsRepository =
            SubRedditsRepositoryImpl()
}
package com.zalesskyi.redditclient

import android.app.Application
import com.zalesskyi.android.weatherapp.data.database.DatabaseCreator
import com.zalesskyi.redditclient.di.ApiModule
import com.zalesskyi.redditclient.di.DaggerAppComponent
import com.zalesskyi.redditclient.di.InteractorsModule

class RedditApp : Application() {

    companion object {

        lateinit var instance: RedditApp
    }

    lateinit var component: DaggerAppComponent

    override fun onCreate() {
        super.onCreate()
        DatabaseCreator.createDb(this)
        instance = this
        initAppComponent()
    }

    private fun initAppComponent() {
        component = DaggerAppComponent
            .builder()
            .apiModule(ApiModule())
            .interactorsModule(InteractorsModule())
            .build() as DaggerAppComponent
    }
}
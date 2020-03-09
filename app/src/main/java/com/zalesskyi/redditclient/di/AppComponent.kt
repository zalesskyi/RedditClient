package com.zalesskyi.redditclient.di

import com.zalesskyi.redditclient.ui.list.RedditsListVM
import dagger.Component

@AppScope
@Component(modules = [ApiModule::class, DbModule::class, InteractorsModule::class])
interface AppComponent {

    fun inject(viewModel: RedditsListVM)
}
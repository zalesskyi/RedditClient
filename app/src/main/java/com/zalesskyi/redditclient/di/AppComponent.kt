package com.zalesskyi.redditclient.di

import com.zalesskyi.redditclient.ui.list.RedditsListVM
import dagger.Component

@Component(modules = [ApiModule::class, InteractorsModule::class])
interface AppComponent {

    fun inject(viewModel: RedditsListVM)
}
package com.zalesskyi.redditclient.ui

import android.net.Uri
import android.os.Bundle
import android.view.View.NO_ID
import androidx.browser.customtabs.CustomTabsIntent
import com.zalesskyi.redditclient.R
import com.zalesskyi.redditclient.ui.base.BaseLifecycleActivity
import com.zalesskyi.redditclient.ui.list.RedditsListCallback
import com.zalesskyi.redditclient.ui.list.RedditsListFragment

class MainActivity : BaseLifecycleActivity<MainVM>(),
    RedditsListCallback {

    override val viewModelClass = MainVM::class.java

    override val containerId = R.id.flContainer

    override val layoutId = R.layout.activity_main

    override fun getProgressBarId() = NO_ID

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        replaceFragment(RedditsListFragment.newInstance(), false)
    }

    override fun openDetail(url: String) {
        CustomTabsIntent.Builder()
            .setStartAnimations(this, R.anim.slide_in_right, R.anim.slide_out_left)
            .setExitAnimations(this, R.anim.slide_in_left, R.anim.slide_out_right)
            .build()
            .launchUrl(this, Uri.parse(url))
    }
}

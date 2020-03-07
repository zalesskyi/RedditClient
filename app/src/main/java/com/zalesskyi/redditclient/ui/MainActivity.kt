package com.zalesskyi.redditclient.ui

import android.os.Bundle
import android.view.View.NO_ID
import com.zalesskyi.redditclient.R
import com.zalesskyi.redditclient.ui.base.BaseLifecycleActivity
import com.zalesskyi.redditclient.ui.list.RedditsListFragment

class MainActivity : BaseLifecycleActivity<MainVM>() {

    override val viewModelClass = MainVM::class.java

    override val containerId = R.id.flContainer

    override val layoutId = R.layout.activity_main

    override fun getProgressBarId() = NO_ID

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        replaceFragment(RedditsListFragment.newInstance(), false)
    }
}

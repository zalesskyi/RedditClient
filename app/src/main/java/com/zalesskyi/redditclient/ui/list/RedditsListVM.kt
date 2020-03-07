package com.zalesskyi.redditclient.ui.list

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.zalesskyi.redditclient.AppContract.PAGE_LIMIT
import com.zalesskyi.redditclient.RedditApp
import com.zalesskyi.redditclient.data.interactors.RedditInteractor
import com.zalesskyi.redditclient.data.models.SubReddit
import com.zalesskyi.redditclient.ui.base.BaseLifecycleVM
import javax.inject.Inject

class RedditsListVM(application: Application) : BaseLifecycleVM(application) {

    init {
        RedditApp.instance.component.inject(this)
    }

    val redditsLD = MutableLiveData<List<SubReddit>>()

    @Inject
    lateinit var interactor: RedditInteractor

    private var lastRedditName: String? = null

    fun loadReddits() {
        interactor.getTopReddits(PAGE_LIMIT, lastRedditName)
                .doOnSuccess { reddits ->
                    lastRedditName = reddits.lastOrNull()?.name
                }
                .doAsync(redditsLD)
    }
}
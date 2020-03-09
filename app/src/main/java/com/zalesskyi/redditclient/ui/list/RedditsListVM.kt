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

    val moreRedditsLD = MutableLiveData<List<SubReddit>>()

    @Inject
    lateinit var interactor: RedditInteractor

    private var lastReddit: SubReddit? = null

    fun loadReddits() {
        interactor.getTopReddits(PAGE_LIMIT, lastReddit?.name)
                .doOnSuccess { reddits ->
                    lastReddit = reddits.lastOrNull()
                }
                .doAsync(redditsLD)
    }

    fun loadMoreReddits() {
        interactor.getTopReddits(PAGE_LIMIT, lastReddit?.name)
            .doOnSuccess { reddits ->
                lastReddit = reddits.lastOrNull()
            }
            .doAsync(moreRedditsLD)
    }
}
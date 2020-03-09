package com.zalesskyi.redditclient.ui.list

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.View.NO_ID
import androidx.lifecycle.Observer
import com.zalesskyi.redditclient.BuildConfig
import com.zalesskyi.redditclient.R
import com.zalesskyi.redditclient.data.models.SubReddit
import com.zalesskyi.redditclient.ui.base.BaseListFragment
import com.zalesskyi.redditclient.ui.list.adapter.RedditsAdapter
import com.zalesskyi.redditclient.ui.list.adapter.RedditsAdapterListener
import com.zalesskyi.redditclient.utils.bindInterfaceOrThrow
import kotlinx.android.synthetic.main.fragment_list.*

class RedditsListFragment : BaseListFragment<RedditsListVM, SubReddit>(),
        RedditsAdapterListener {

    companion object {

        fun newInstance() = RedditsListFragment()
    }

    override val viewModelClass = RedditsListVM::class.java

    override val layoutId = R.layout.fragment_list

    override val recyclerViewId = R.id.rvList

    override val noResultViewId = NO_ID

    override val refreshLayoutId = R.id.srlRefresh

    private var adapter: RedditsAdapter? = null

    private var callback: RedditsListCallback? = null

    private val redditsObserver = Observer<List<SubReddit>> { reddits ->
        onInitialDataLoaded(reddits)
    }

    private val moreRedditsObserver = Observer<List<SubReddit>> { reddits ->
        onDataRangeLoaded(reddits)
    }

    override val progressObserver = Observer<Boolean> {
        srlRefresh?.isRefreshing = it
    }

    override fun observeLiveData(viewModel: RedditsListVM) = viewModel.run {
        redditsLD.observe(this@RedditsListFragment, redditsObserver)
        moreRedditsLD.observe(this@RedditsListFragment, moreRedditsObserver)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callback = bindInterfaceOrThrow(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadInitial()
    }

    override fun onDetach() {
        callback = null
        super.onDetach()
    }

    override fun getAdapter() = adapter ?: RedditsAdapter(ctx, this).also {
        adapter = it
    }

    override fun loadInitial() = viewModel.loadReddits()

    override fun loadMoreData() = viewModel.loadMoreReddits()

    override fun onItemClick(item: SubReddit) {
        item.permalink?.let { link ->
            callback?.openDetail("${BuildConfig.REDDIT_API_ENDPOINT}$link")
        }
    }
}
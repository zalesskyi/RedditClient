package com.zalesskyi.redditclient.ui.list

import android.os.Bundle
import android.view.View
import android.view.View.NO_ID
import androidx.lifecycle.Observer
import com.zalesskyi.redditclient.R
import com.zalesskyi.redditclient.data.models.SubReddit
import com.zalesskyi.redditclient.ui.base.BaseListFragment
import com.zalesskyi.redditclient.ui.list.adapter.RedditsAdapter
import com.zalesskyi.redditclient.ui.list.adapter.RedditsAdapterListener

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

    private val redditsObserver = Observer<List<SubReddit>> { reddits ->
        adapter?.run {
            addAll(reddits)
            notifyDataSetChanged()
        }
    }

    override fun observeLiveData(viewModel: RedditsListVM) = viewModel.run {
        redditsLD.observe(this@RedditsListFragment, redditsObserver)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadInitial()
    }

    override fun getAdapter() = adapter ?: RedditsAdapter(ctx, this).also {
        adapter = it
    }

    override fun loadInitial() = viewModel.loadReddits()

    override fun loadMoreData() = viewModel.loadReddits()
}
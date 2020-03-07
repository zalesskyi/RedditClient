package com.zalesskyi.redditclient.ui.list.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.zalesskyi.redditclient.R
import com.zalesskyi.redditclient.data.models.SubReddit
import com.zalesskyi.redditclient.ui.base.BaseRecyclerViewAdapter
import kotlinx.android.synthetic.main.item_reddit.view.*
import java.lang.ref.WeakReference

class RedditsAdapter(context: Context, listener: RedditsAdapterListener)
    : BaseRecyclerViewAdapter<SubReddit, RedditsAdapter.RedditHolder>(context) {

    private val listenerRef = WeakReference(listener)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RedditHolder =
            RedditHolder.newInstance(inflater, parent, listenerRef.get())

    override fun onBindViewHolder(holder: RedditHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class RedditHolder(view: View) : RecyclerView.ViewHolder(view) {

        companion object {

            fun newInstance(inflater: LayoutInflater, parent: ViewGroup, listener: RedditsAdapterListener?) =
                    RedditHolder(inflater.inflate(R.layout.item_reddit, parent, false))
        }

        fun bind(reddit: SubReddit) {
            itemView.apply {
                tvName?.text = reddit.name
            }
        }
    }
}
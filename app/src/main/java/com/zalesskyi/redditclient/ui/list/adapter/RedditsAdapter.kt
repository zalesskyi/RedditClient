package com.zalesskyi.redditclient.ui.list.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.zalesskyi.redditclient.R
import com.zalesskyi.redditclient.data.models.SubReddit
import com.zalesskyi.redditclient.extensions.hide
import com.zalesskyi.redditclient.extensions.loadImage
import com.zalesskyi.redditclient.ui.base.BaseRecyclerViewAdapter
import com.zalesskyi.redditclient.utils.DateUtils.getFormattedDate
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

    class RedditHolder(view: View,
                       private val listener: RedditsAdapterListener?) : RecyclerView.ViewHolder(view) {

        companion object {

            fun newInstance(inflater: LayoutInflater, parent: ViewGroup, listener: RedditsAdapterListener?) =
                    RedditHolder(inflater.inflate(R.layout.item_reddit, parent, false), listener)
        }

        fun bind(reddit: SubReddit) {
            itemView.apply {
                reddit.run {
                    url?.let {
                        ivBanner?.loadImage(it, R.drawable.ic_placeholder)
                    } ?: ivBanner?.hide()
                    tvAuthor?.text = authorName
                    created?.let {
                        tvTime?.text = context?.getString(R.string.time, getFormattedDate(it))
                    }
                    tvNumComments?.text = context?.getString(R.string.comments, commentsNumber)
                    tvSubject?.text = subRedditName
                    tvTitle?.text = title
                    tvScore?.text = context?.getString(R.string.score, score)
                }
                setOnClickListener {
                    listener?.onItemClick(reddit)
                }
            }
        }
    }
}
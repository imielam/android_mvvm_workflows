/*
 * Copyright (c) 2017.  All rights reserved - Maciej Imiela.
 */

package mobilehexers.eu.driversweek.repository.list

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_repository_list.view.repository_list_item_text
import mobilehexers.eu.domain.base.recycler.ViewType
import mobilehexers.eu.domain.repository.list.entity.RepositoryListItem
import mobilehexers.eu.driversweek.R
import mobilehexers.eu.driversweek.extensions.inflate
import mobilehexers.eu.uibase.base.recycler.RecyclerViewOnItemClickListener
import mobilehexers.eu.uibase.base.recycler.ViewTypeDelegateAdapter

class RepositoryItemDelegateAdapter : ViewTypeDelegateAdapter {
    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder = RepositoryItemViewHolder(parent)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType, listener: RecyclerViewOnItemClickListener) {
        holder as RepositoryItemViewHolder
        holder.bind(item, listener)
    }

    class RepositoryItemViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(parent.inflate(R.layout.item_repository_list)) {
        fun bind(item: ViewType, listener: RecyclerViewOnItemClickListener) = with(itemView) {
            item as RepositoryListItem
            itemView.setOnClickListener { listener.onItemClick(item) }
            repository_list_item_text.text = item.name
        }
    }
}
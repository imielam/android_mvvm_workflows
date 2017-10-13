/*
 * Copyright (c) 2017. All rights reserved - Maciej Imiela.
 */

package mobilehexers.eu.driversweek.repository.list

import android.support.v4.util.SparseArrayCompat
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import mobilehexers.eu.domain.recycler.ViewType
import mobilehexers.eu.uibase.base.recycler.AdapterConstants
import mobilehexers.eu.uibase.base.recycler.LoadingDelegateAdapter
import mobilehexers.eu.uibase.base.recycler.ViewTypeDelegateAdapter

class RepositoryListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items: ArrayList<ViewType>
    private var delegateAdapters = SparseArrayCompat<ViewTypeDelegateAdapter>()
    private val loadingItem = object : ViewType {
        override fun getViewType() = AdapterConstants.LOADING
    }

    init {
        delegateAdapters.put(AdapterConstants.LOADING, LoadingDelegateAdapter())
        delegateAdapters.put(AdapterConstants.REPOSITORY_ITEM, RepositoryItemDelegateAdapter())
        items = ArrayList()
        items.add(loadingItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = delegateAdapters.get(viewType).onCreateViewHolder(parent)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) = delegateAdapters.get(getItemViewType(position)).onBindViewHolder(holder,
            this.items[position])

    override fun getItemCount() = items.size

    override fun getItemViewType(position: Int) = this.items[position].getViewType()

    fun addRepositories(repositories: List<ViewType>) {
        val initPosition = items.size - 1
        items.removeAt(initPosition)
        notifyItemRemoved(initPosition)
        items.addAll(repositories)
        items.add(loadingItem)
        notifyItemRangeInserted(0, items.size)
    }

    fun getRepositories(): List<RepositoryListItem> {
        return items.filter { it.getViewType() == AdapterConstants.REPOSITORY_ITEM }.map { it as RepositoryListItem }
    }

    private fun getLastPosition() = if (items.lastIndex == -1) 0 else items.lastIndex
}

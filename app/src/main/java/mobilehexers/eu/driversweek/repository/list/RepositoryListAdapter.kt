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

    private var allRepositoryItems = mutableListOf<ViewType>()
    private var adapterItems = mutableListOf<ViewType>()
    private var limitedItems = allRepositoryItems

    private var delegateAdapters = SparseArrayCompat<ViewTypeDelegateAdapter>()

    private val loadingItem = object : ViewType {
        override fun getViewType() = AdapterConstants.LOADING
    }

    init {
        delegateAdapters.put(AdapterConstants.LOADING, LoadingDelegateAdapter())
        delegateAdapters.put(AdapterConstants.REPOSITORY_ITEM, RepositoryItemDelegateAdapter())
        adapterItems.add(loadingItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = delegateAdapters.get(viewType).onCreateViewHolder(parent)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) = delegateAdapters.get(getItemViewType(position)).onBindViewHolder(holder,
            this.adapterItems[position])

    override fun getItemCount() = adapterItems.size

    override fun getItemViewType(position: Int) = this.adapterItems[position].getViewType()

    fun addRepositories(repositories: List<ViewType>) {
        allRepositoryItems.addAll(repositories)
        updateAdapter()
    }


    fun limitRepositoriesTo(max: Int) {
        limitedItems = allRepositoryItems.subList(0, max)
        updateAdapter()
    }

    fun removeLimit() {
        limitedItems = allRepositoryItems
        updateAdapter()
    }

    private fun updateAdapter() {
        adapterItems.clear()
        adapterItems.addAll(limitedItems)
        notifyDataSetChanged()
    }
}

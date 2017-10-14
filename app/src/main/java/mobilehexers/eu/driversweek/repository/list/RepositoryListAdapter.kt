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
import mobilehexers.eu.uibase.base.recycler.RecyclerViewOnItemClickListener
import mobilehexers.eu.uibase.base.recycler.ViewTypeDelegateAdapter

class RepositoryListAdapter(listener: RecyclerViewOnItemClickListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var allRepositoryItems = mutableListOf<RepositoryListItem>()
    private var adapterItems = mutableListOf<ViewType>()
    private var filtered = false
    private var filterText = ""
    private var limited = false
    private var limitValue: Int = allRepositoryItems.size
    private val innerListener: RecyclerViewOnItemClickListener

    private var delegateAdapters = SparseArrayCompat<ViewTypeDelegateAdapter>()

    private val loadingItem = object : ViewType {
        override fun getViewType() = AdapterConstants.LOADING
    }

    init {
        delegateAdapters.put(AdapterConstants.LOADING, LoadingDelegateAdapter())
        delegateAdapters.put(AdapterConstants.REPOSITORY_ITEM, RepositoryItemDelegateAdapter())
        adapterItems.add(loadingItem)
        innerListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = delegateAdapters.get(viewType).onCreateViewHolder(parent)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) = delegateAdapters.get(getItemViewType(position)).onBindViewHolder(holder,
            this.adapterItems[position], innerListener)

    override fun getItemCount() = adapterItems.size

    override fun getItemViewType(position: Int) = this.adapterItems[position].getViewType()

    fun addRepositories(repositories: List<RepositoryListItem>) {
        allRepositoryItems.addAll(repositories)
        updateAdapter()
    }

    fun limitRepositoriesTo(max: Int) {
        limited = true
        limitValue = max
        updateAdapter()
    }

    fun removeLimit() {
        limited = false
        limitValue = allRepositoryItems.size
        updateAdapter()
    }

    fun filterRepositories(text: String) {
        filtered = true
        filterText = text
        updateAdapter()
    }

    fun removeFilter() {
        filtered = false
        filterText = ""
        updateAdapter()
    }

    private fun updateAdapter() {
        adapterItems.clear()
        var data: List<RepositoryListItem> = allRepositoryItems
        if (limited) {
            data = limitData(data, limitValue)
        }
        if (filtered) {
            data = filterData(data, filterText)
        }
        adapterItems.addAll(data)
        notifyDataSetChanged()
    }

    private fun filterData(repositoriesList: List<RepositoryListItem>, text: String): List<RepositoryListItem> = repositoriesList.filter {
        it.name.contains(text)
    }.toMutableList()

    private fun limitData(repositoriesList: List<RepositoryListItem>, max: Int) = repositoriesList.subList(0, minOf(max, repositoriesList.size))
}

/*
 * Copyright (c) 2017. All rights reserved - Maciej Imiela.
 */

package mobilehexers.eu.driversweek.repository.list

import android.support.v4.util.SparseArrayCompat
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import mobilehexers.eu.domain.base.model.ApplicationDataSet
import mobilehexers.eu.domain.base.recycler.AdapterConstants
import mobilehexers.eu.domain.base.recycler.ViewType
import mobilehexers.eu.uibase.base.recycler.LoadingDelegateAdapter
import mobilehexers.eu.uibase.base.recycler.RecyclerViewOnItemClickListener
import mobilehexers.eu.uibase.base.recycler.ViewTypeDelegateAdapter

class RepositoryListAdapter(listener: RecyclerViewOnItemClickListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>(), ApplicationDataSet {

    private var adapterItems = mutableListOf<ViewType>()
    private var delegateAdapters = SparseArrayCompat<ViewTypeDelegateAdapter>()

    private val innerListener: RecyclerViewOnItemClickListener
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

    override fun updateWith(data: List<ViewType>) {
        adapterItems.clear()
        adapterItems.addAll(data)
        notifyDataSetChanged()
    }
}

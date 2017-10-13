/*
 * Copyright (c) 2017. All rights reserved - Maciej Imiela.
 */

package mobilehexers.eu.driversweek.repository.list

import android.support.v4.util.SparseArrayCompat
import mobilehexers.eu.domain.recycler.ViewType
import mobilehexers.eu.uibase.base.recycler.AdapterConstants
import mobilehexers.eu.uibase.base.recycler.LoadingDelegateAdapter
import mobilehexers.eu.uibase.base.recycler.ViewTypeDelegateAdapter

/**
 * Created by macie on 13.10.2017.
 */
class RepositoryListAdapter {
    private var items: ArrayList<ViewType>
    private var delegateAdapters = SparseArrayCompat<ViewTypeDelegateAdapter>()
    private val loadingItem = object : ViewType {
        override fun getViewType() = AdapterConstants.LOADING
    }

    init {
        delegateAdapters.put(AdapterConstants.LOADING, LoadingDelegateAdapter())
        items = ArrayList()
        items.add(loadingItem)
    }
}

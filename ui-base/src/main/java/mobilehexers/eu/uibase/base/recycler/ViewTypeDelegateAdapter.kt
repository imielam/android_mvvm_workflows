/*
 * Copyright (c) 2017. All rights reserved - Maciej Imiela.
 */

package mobilehexers.eu.uibase.base.recycler

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import mobilehexers.eu.domain.base.recycler.ViewType

interface ViewTypeDelegateAdapter {

    fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder

    fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType, listener: RecyclerViewOnItemClickListener)
}

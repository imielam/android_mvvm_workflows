/*
 * Copyright (c) 2017. All rights reserved - Maciej Imiela.
 */

package mobilehexers.eu.uibase.base.recycler

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import mobilehexers.eu.domain.recycler.ViewType
import mobilehexers.eu.ui_base.R
import mobilehexers.eu.uibase.extensions.inflate

class LoadingDelegateAdapter : ViewTypeDelegateAdapter {

    override fun onCreateViewHolder(parent: ViewGroup) = LoadingItemViewHolder(parent)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType) {
    }

    class LoadingItemViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(parent.inflate(R.layout.repository_item_loading))
}

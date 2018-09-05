/*
 * Copyright (c) 2018.  All rights reserved - Maciej Imiela.
 */

package mobilehexers.eu.demo.weather.list

import android.arch.paging.PagedListAdapter
import android.support.v7.util.DiffUtil
import android.view.LayoutInflater
import android.view.ViewGroup
import mobilehexers.eu.domain.weather.list.entity.City
import mobilehexers.eu.demo.R
import mobilehexers.eu.demo.weather.list.item.CityListItem
import mobilehexers.eu.demo.weather.list.item.OnItemClickListener

class CityListAdapter(private val clickListener: OnItemClickListener) : PagedListAdapter<City, CityListItem>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityListItem {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_wether_list, parent, false)
        return CityListItem(view)
    }

    override fun onBindViewHolder(holder: CityListItem, position: Int) {
        val city = getItem(position)
        if (city != null) {
            holder.bindTo(city, clickListener)
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<City>() {
            override fun areItemsTheSame(oldConcert: City, newConcert: City): Boolean = oldConcert.id == newConcert.id

            override fun areContentsTheSame(oldConcert: City, newConcert: City): Boolean = oldConcert == newConcert
        }
    }
}
/*
 * Copyright (c) 2018.  All rights reserved - Maciej Imiela.
 */

package mobilehexers.eu.demo.weather.list.item

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import kotlinx.android.synthetic.main.item_wether_list.view.weather_list_item_text
import mobilehexers.eu.domain.weather.list.entity.City

class CityListItem(private val view: View) : RecyclerView.ViewHolder(view) {
    private val cityNameTextField: TextView = view.weather_list_item_text

    fun bindTo(city: City, clickListener: OnItemClickListener) {
        cityNameTextField.text = city.name
        view.setOnClickListener { clickListener.onClick(city) }
    }
}
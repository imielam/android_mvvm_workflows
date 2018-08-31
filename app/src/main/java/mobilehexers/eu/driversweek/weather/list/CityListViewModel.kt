/*
 * Copyright (c) 2018.  All rights reserved - Maciej Imiela.
 */

package mobilehexers.eu.driversweek.weather.list

import android.arch.paging.PagedList
import io.reactivex.Flowable
import mobilehexers.eu.domain.base.rx.SchedulerProvider
import mobilehexers.eu.domain.base.viewmodel.FragmentViewModel
import mobilehexers.eu.domain.base.workflow.Workflow
import mobilehexers.eu.domain.weather.list.entity.City
import mobilehexers.eu.domain.weather.model.WeatherModel

class CityListViewModel(
        private val workflow: Workflow,
        private val model: WeatherModel,
        private val schedulerProvider: SchedulerProvider
) :
        FragmentViewModel() {

    val cities get() = model.cities

    override fun onViewCreated() {}

    fun itemClicked(item: City) {
        showDetails(item)
    }

    private fun showDetails(city: City) {
        model.clickedCity = city
        workflow.next()
    }

    fun getFilteredData(filtered: Flowable<String>): Flowable<PagedList<City>> =
            filtered
                    .flatMap { text ->
                        if (text.isNotBlank()) {
                            model.getCitiesWithFilter(text)
                        } else {
                            model.cities
                        }
                    }
                    .subscribeOn(schedulerProvider.ioThread)
                    .observeOn(schedulerProvider.mainThread)
}

/*
 * Copyright (c) 2018.  All rights reserved - Maciej Imiela.
 */

package mobilehexers.eu.driversweek.weather.details

import android.databinding.ObservableField
import android.databinding.ObservableInt
import android.util.Log
import android.view.View
import mobilehexers.eu.domain.base.rx.SchedulerProvider
import mobilehexers.eu.domain.base.viewmodel.FragmentViewModel
import mobilehexers.eu.domain.extensions.logTag
import mobilehexers.eu.domain.weather.details.entity.WeatherDetails
import mobilehexers.eu.domain.weather.model.WeatherModel
import mobilehexers.eu.driversweek.R
import mobilehexers.eu.driversweek.weather.details.item.WeatherDetailsItem
import mobilehexers.eu.driversweek.weather.details.item.toItem

class WeatherDetailsViewModel(
        private val model: WeatherModel,
        private val schedulerProvider: SchedulerProvider
) : FragmentViewModel() {

    val details = ObservableField<WeatherDetailsItem>()
    val detailsVisibility = ObservableInt(View.GONE)
    val progressVisibility = ObservableInt(View.VISIBLE)
    val errorMessageVisibility = ObservableInt(View.GONE)
    val errorMessageId = ObservableInt(R.string.empty)

    override fun onViewCreated() {
        model.clickedCity?.let {
            addDisposable(model.getDetailsFor(it.id)
                    .subscribeOn(schedulerProvider.ioThread)
                    .observeOn(schedulerProvider.mainThread)
                    .subscribe(
                            { showNextItem(it) },
                            this::handleError
                    )
            )
        } ?: handleError(IllegalStateException("No clicked city set!"))
    }

    private fun handleError(error: Throwable) {
        Log.e(logTag, error.localizedMessage)
        errorMessageId.set(R.string.weather_details_cached_data_no)
        errorMessageVisibility.set(View.VISIBLE)
        progressVisibility.set(View.GONE)
    }

    private fun showNextItem(next: WeatherDetails) {
        if (next.offline) {
            errorMessageId.set(R.string.weather_details_cached_data)
            errorMessageVisibility.set(View.VISIBLE)
        }
        progressVisibility.set(View.GONE)
        detailsVisibility.set(View.VISIBLE)
        details.set(next.toItem())
    }
}

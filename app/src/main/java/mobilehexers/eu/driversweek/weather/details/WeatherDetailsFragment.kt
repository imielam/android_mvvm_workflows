/*
 * Copyright (c) 2018.  All rights reserved - Maciej Imiela.
 */

package mobilehexers.eu.driversweek.weather.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import mobilehexers.eu.domain.base.viewmodel.FragmentViewModel
import mobilehexers.eu.driversweek.databinding.FragmentWetherDetailBinding
import mobilehexers.eu.uibase.base.android.BaseFragment
import javax.inject.Inject

class WeatherDetailsFragment : BaseFragment() {

    @Inject
    override lateinit var viewModel: FragmentViewModel

    companion object {
        fun newInstance(): WeatherDetailsFragment {
            val fragment = WeatherDetailsFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val detailBinding = FragmentWetherDetailBinding.inflate(inflater)
        detailBinding.viewModel = viewModel as WeatherDetailsViewModel
        return detailBinding.root
    }
}
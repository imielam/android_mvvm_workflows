/*
 * Copyright (c) 2018.  All rights reserved - Maciej Imiela.
 */

package mobilehexers.eu.driversweek.weather.activity

import android.support.v4.app.Fragment
import android.util.Log
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import mobilehexers.eu.domain.base.workflow.State
import mobilehexers.eu.domain.base.workflow.Workflow
import mobilehexers.eu.domain.extensions.logTag
import mobilehexers.eu.driversweek.main.activity.MainActivity
import mobilehexers.eu.driversweek.weather.details.WeatherDetailsFragment
import mobilehexers.eu.driversweek.weather.list.CityListFragment
import mobilehexers.eu.presentation.wether.workflow.WeatherEnum
import mobilehexers.eu.presentation.wether.workflow.WeatherState
import mobilehexers.eu.presentation.wether.workflow.WeatherWorkflow
import mobilehexers.eu.uibase.base.android.BaseActivity
import javax.inject.Inject

class WeatherActivity : BaseActivity(), HasSupportFragmentInjector {

    @Inject
    lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>
    @Inject
    lateinit var mWorkflow: WeatherWorkflow

    override fun getWorkflowInstance(): Workflow = mWorkflow

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = fragmentInjector

    override fun handleStateChange(state: State) {
        if (state is WeatherState) {
            Log.d(logTag, "handleStateChange: $state")
            when (state.currentState) {
                WeatherEnum.LIST -> switchFragment(CityListFragment.newInstance())
                WeatherEnum.DETAILS -> addFragment(WeatherDetailsFragment.newInstance())
                WeatherEnum.ENDED -> finishWorkflow()
                else -> Log.w(logTag, "Unsupported state: $state")
            }
        } else {
            Log.w(logTag, "Wrong state type: " + state::class)
        }
    }

    override fun finishWorkflow() {
        mWorkflow.end()
        startActivity(MainActivity::class)
        finish()
    }
}

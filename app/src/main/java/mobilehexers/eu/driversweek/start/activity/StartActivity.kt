/*
 * Copyright (c) 2018.  All rights reserved - Maciej Imiela.
 */

package mobilehexers.eu.driversweek.start.activity

import android.support.v4.app.Fragment
import android.util.Log
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import mobilehexers.eu.domain.base.workflow.State
import mobilehexers.eu.domain.extensions.logTag
import mobilehexers.eu.driversweek.main.activity.MainActivity
import mobilehexers.eu.driversweek.start.loading.LoadingFragment
import mobilehexers.eu.presentation.start.workflow.StartEnum
import mobilehexers.eu.presentation.start.workflow.StartState
import mobilehexers.eu.presentation.start.workflow.StartWorkflow
import mobilehexers.eu.uibase.base.android.BaseActivity
import javax.inject.Inject

class StartActivity : BaseActivity(), HasSupportFragmentInjector {

    @Inject
    lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>
    @Inject
    internal lateinit var workflow: StartWorkflow

    override fun getWorkflowInstance() = workflow

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = fragmentInjector

    override fun handleStateChange(state: State) {
        if (state is StartState) {
            Log.d(logTag, "handleStateChange: " + state)
            when (state.currentState) {
                StartEnum.WORKING -> switchFragment(LoadingFragment.newInstance())
                StartEnum.ENDED -> finishWorkflow()
                else -> Log.w(logTag, "Unsupported state: " + state)
            }
        } else {
            Log.w(logTag, "Wrong state type: " + state::class)
        }
    }

    override fun finishWorkflow() {
        workflow.end()
        startActivity(MainActivity::class)
        finish()
    }
}

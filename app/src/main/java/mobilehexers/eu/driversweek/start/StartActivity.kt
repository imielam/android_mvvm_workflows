/*
 * Copyright (c) 2017.  All rights reserved - Maciej Imiela.
 */

package mobilehexers.eu.driversweek.start

import android.util.Log
import mobilehexers.eu.domain.workflow.base.State
import mobilehexers.eu.domain.workflow.start.StartEnum
import mobilehexers.eu.domain.workflow.start.StartState
import mobilehexers.eu.domain.workflow.start.StartWorkflow
import mobilehexers.eu.driversweek.main.MainActivity
import mobilehexers.eu.uibase.base.android.BaseActivity
import mobilehexers.eu.uibase.extensions.logTag
import javax.inject.Inject

class StartActivity : BaseActivity() {

    @Inject internal lateinit var workflow: StartWorkflow

    override fun getWorkflowInstance() = workflow

    override fun handleStateChange(state: State) {
        if (state is StartState) {
            Log.d(logTag, "handleStateChange: " + state)
            when (state.currentState) {
                StartEnum.ENDED -> startActivity(MainActivity::class)
                else -> Log.w(logTag, "Unsupported state: " + state)
            }
        } else {
            Log.w(logTag, "Wrong state type: " + state::class)
        }
    }
}

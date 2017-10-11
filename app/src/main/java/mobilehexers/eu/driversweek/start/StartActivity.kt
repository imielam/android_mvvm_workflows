/*
 * Copyright (c) 2017.  All rights reserved - Maciej Imiela.
 */

package mobilehexers.eu.driversweek.start

import android.os.Bundle
import android.util.Log
import mobilehexers.eu.domain.workflow.base.State
import mobilehexers.eu.domain.workflow.start.StartEnum
import mobilehexers.eu.domain.workflow.start.StartState
import mobilehexers.eu.domain.workflow.start.StartWorkflow
import mobilehexers.eu.driversweek.base.android.BaseActivity
import mobilehexers.eu.driversweek.extensions.logTag
import mobilehexers.eu.driversweek.main.MainActivity
import javax.inject.Inject

class StartActivity : BaseActivity() {
    @Inject
    internal lateinit var workflow: StartWorkflow

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(logTag, "onCreate")
    }

    override fun onResume() {
        Log.d(logTag, "onResume")
        super.onResume()
        workflow.next()
    }

    override fun handleStateChange(state: State) {
        when ((state as StartState).currentState) {
            StartEnum.ENDED -> startActivity(MainActivity::class)
        }
    }

    override fun getWorkflowInstance() = workflow
}

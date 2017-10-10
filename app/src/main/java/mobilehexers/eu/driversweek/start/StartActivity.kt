/*
 * Copyright (c) 2017.  All rights reserved - Maciej Imiela.
 */

package mobilehexers.eu.driversweek.start

import android.content.Intent
import android.os.Bundle
import android.util.Log
import dagger.android.AndroidInjection
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Action
import io.reactivex.functions.Consumer
import mobilehexers.eu.domain.workflow.start.StartEnum
import mobilehexers.eu.domain.workflow.start.StartState
import mobilehexers.eu.domain.workflow.start.StartWorkflow
import mobilehexers.eu.driversweek.R
import mobilehexers.eu.driversweek.base.android.BaseActivity
import mobilehexers.eu.driversweek.extensions.logTag
import mobilehexers.eu.driversweek.main.MainActivity
import javax.inject.Inject

class StartActivity : BaseActivity() {
    @Inject
    lateinit var workflow: StartWorkflow
    private var disposable: Disposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(logTag, "onCreate")
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
        if (savedInstanceState == null) {
            disposable = workflow.init(Consumer { state -> handleStateChange(state as StartState) }, Consumer { }, Action { disposable?.dispose() })
        }
    }

    override fun onResume() {
        Log.d(logTag, "onResume")
        super.onResume()
        workflow.next()
    }

    private fun handleStateChange(state: StartState) {
        when (state.currentState) {
            StartEnum.ENDED -> startMainActivity()
        }
    }

    private fun startMainActivity() {
        val newIntent = Intent(baseContext, MainActivity::class.java)
        startActivity(newIntent)
        finish()
    }
}
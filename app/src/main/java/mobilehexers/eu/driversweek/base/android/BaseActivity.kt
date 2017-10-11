/*
 * Copyright (c) 2017.  All rights reserved - Maciej Imiela.
 */

package mobilehexers.eu.driversweek.base.android

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import dagger.android.AndroidInjection
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Action
import io.reactivex.functions.Consumer
import mobilehexers.eu.domain.workflow.base.State
import mobilehexers.eu.domain.workflow.base.Workflow
import mobilehexers.eu.domain.workflow.start.StartState
import mobilehexers.eu.driversweek.R
import mobilehexers.eu.driversweek.extensions.logTag
import kotlin.reflect.KClass

/**
 * Created by mimiela on 10.10.17.
 */
abstract class BaseActivity : AppCompatActivity() {
    private val disposables: CompositeDisposable = CompositeDisposable()
    private var workflowDisposable: Disposable? = null

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
        if (savedInstanceState == null) {
            val disposable = getWorkflowInstance().init(
                    Consumer { state -> handleStateChange(state as StartState) },
                    Consumer { throwable -> Log.e(logTag, throwable.toString()) },
                    Action { disposeWorkflow() })
            addWorkflowDisposable(disposable)
        }
    }

    override fun onPause() {
        super.onPause()
        disposable()
    }

    private fun disposable() {
        disposables.clear()
    }


    protected fun addDisposable(disposable: Disposable) {
        disposables.add(disposable)
    }

    protected fun addWorkflowDisposable(disposable: Disposable) {
        workflowDisposable = disposable
    }

    protected fun disposeWorkflow() {
        workflowDisposable?.dispose()
        workflowDisposable = null
    }

    protected abstract fun getWorkflowInstance(): Workflow

    protected fun startActivity(activityClass: KClass<out BaseActivity>) {
        val newIntent = Intent(baseContext, activityClass.java)
        startActivity(newIntent)
        finish()
    }

    protected abstract fun handleStateChange(state: State)
}

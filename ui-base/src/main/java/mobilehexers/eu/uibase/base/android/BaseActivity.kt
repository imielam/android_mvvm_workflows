/*
 * Copyright (c) 2017.  All rights reserved - Maciej Imiela.
 */

package mobilehexers.eu.uibase.base.android

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.util.Log
import dagger.android.AndroidInjection
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Action
import io.reactivex.functions.Consumer
import mobilehexers.eu.domain.workflow.base.State
import mobilehexers.eu.domain.workflow.base.Workflow
import mobilehexers.eu.ui_base.R
import mobilehexers.eu.uibase.extensions.logTag
import kotlin.reflect.KClass

abstract class BaseActivity : AppCompatActivity() {

    private val disposables: CompositeDisposable = CompositeDisposable()
    private var workflowDisposable: Disposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        setContentView(R.layout.activity_base)
        initWorkflow(savedInstanceState)
    }

    private fun initWorkflow(savedInstanceState: Bundle?) {
        val workflowInstance = getWorkflowInstance()
        if (savedInstanceState == null) {
            val disposable = workflowInstance.init(Consumer { state -> handleStateChange(state) },
                    Consumer { throwable -> Log.e(logTag, throwable.toString()) }, Action { disposeWorkflow() })
            workflowDisposable = disposable
        }
        workflowInstance.next()
        Log.d(logTag, "Workflow initialized: " + workflowInstance)
    }

    private fun disposeWorkflow() {
        workflowDisposable?.dispose()
        workflowDisposable = null
    }

    override fun onPause() {
        super.onPause()
        disposeSubscriptions()
    }

    private fun disposeSubscriptions() {
        disposables.clear()
    }

    protected abstract fun getWorkflowInstance(): Workflow

    protected abstract fun handleStateChange(state: State)

    protected fun startActivity(activityClass: KClass<out BaseActivity>) {
        val newIntent = Intent(baseContext, activityClass.java)
        startActivity(newIntent)
        finish()
    }

    protected fun switchFragment(fragment: Fragment) {
        changeFragment(fragment, false)
    }

    protected fun addFragment(fragment: Fragment) {
        changeFragment(fragment, true)
    }

    private fun changeFragment(fragment: Fragment, addToBackStack: Boolean) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.base_container, fragment)
        if (addToBackStack) {
            fragmentTransaction.addToBackStack(null)
        }
        fragmentTransaction.commit()
    }

    protected fun addDisposable(disposable: Disposable) {
        disposables.add(disposable)
    }

    protected abstract fun finishActivity()
}

/*
 * Copyright (c) 2017.  All rights reserved - Maciej Imiela.
 */

package mobilehexers.eu.driversweek.courses.activity

import android.app.AlertDialog
import android.support.v4.app.Fragment
import android.util.Log
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import mobilehexers.eu.domain.base.workflow.State
import mobilehexers.eu.domain.base.workflow.Workflow
import mobilehexers.eu.domain.extensions.logTag
import mobilehexers.eu.driversweek.R
import mobilehexers.eu.driversweek.courses.list.CoursesListFragment
import mobilehexers.eu.driversweek.main.activity.MainActivity
import mobilehexers.eu.presentation.courses.workflow.CoursesEnum
import mobilehexers.eu.presentation.courses.workflow.CoursesState
import mobilehexers.eu.presentation.courses.workflow.CoursesWorkflow
import mobilehexers.eu.uibase.base.android.BaseActivity
import javax.inject.Inject

class CoursesActivity : BaseActivity(), HasSupportFragmentInjector {

    @Inject lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>
    @Inject lateinit var workflow: CoursesWorkflow

    override fun getWorkflowInstance(): Workflow = workflow

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = fragmentInjector

    override fun handleStateChange(state: State) {
        if (state is CoursesState) {
            Log.d(logTag, "handleStateChange: " + state)
            when (state.currentState) {
                CoursesEnum.LIST -> switchFragment(CoursesListFragment.newInstance())
                CoursesEnum.SUCCESS -> showSuccessDialog()
                CoursesEnum.ERROR -> showErrorDialog()
                CoursesEnum.ENDED -> finishWorkflow()
                else -> Log.w(logTag, "Unsupported state: " + state)
            }
        } else {
            Log.w(logTag, "Wrong state type: " + state::class)
        }
    }

    private fun showSuccessDialog() {
        AlertDialog.Builder(this).apply {
            setMessage(R.string.courses_list_dialog_success_message)
            setTitle(R.string.courses_list_dialog_success_header)
            setPositiveButton(R.string.courses_list_dialog_ok) { dialog, _ -> dialog?.dismiss() }
        }.show()
    }

    private fun showErrorDialog() {
        AlertDialog.Builder(this).apply {
            setMessage(R.string.courses_list_dialog_error_message)
            setTitle(R.string.courses_list_dialog_error_header)
            setPositiveButton(R.string.courses_list_dialog_ok) { dialog, _ -> dialog?.dismiss() }
        }.show()
    }

    override fun finishWorkflow() {
        workflow.end()
        startActivity(MainActivity::class)
        finish()
    }
}

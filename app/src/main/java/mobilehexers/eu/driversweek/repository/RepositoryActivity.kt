/*
 * Copyright (c) 2017.  All rights reserved - Maciej Imiela.
 */

package mobilehexers.eu.driversweek.repository

import android.support.v4.app.Fragment
import android.util.Log
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import mobilehexers.eu.domain.base.workflow.State
import mobilehexers.eu.domain.base.workflow.Workflow
import mobilehexers.eu.driversweek.main.MainActivity
import mobilehexers.eu.driversweek.repository.details.RepositoryDetailsFragment
import mobilehexers.eu.driversweek.repository.list.RepositoryListFragment
import mobilehexers.eu.presentation.repository.workflow.RepositoryEnum
import mobilehexers.eu.presentation.repository.workflow.RepositoryState
import mobilehexers.eu.presentation.repository.workflow.RepositoryWorkflow
import mobilehexers.eu.uibase.base.android.BaseActivity
import mobilehexers.eu.uibase.extensions.logTag
import javax.inject.Inject

class RepositoryActivity : BaseActivity(), HasSupportFragmentInjector {

    @Inject lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>
    @Inject lateinit var workflow: RepositoryWorkflow

    override fun getWorkflowInstance(): Workflow = workflow

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = fragmentInjector

    override fun handleStateChange(state: State) {
        if (state is RepositoryState) {
            Log.d(logTag, "handleStateChange: " + state)
            when (state.currentState) {
                RepositoryEnum.LIST -> switchFragment(RepositoryListFragment.newInstance())
                RepositoryEnum.DETAILS -> addFragment(RepositoryDetailsFragment.newInstance())
                RepositoryEnum.ENDED -> finishWorkflow()
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

package mobilehexers.eu.driversweek.main

import android.support.v4.app.Fragment
import android.util.Log
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import mobilehexers.eu.domain.workflow.base.State
import mobilehexers.eu.domain.workflow.base.Workflow
import mobilehexers.eu.domain.workflow.main.MainEnum
import mobilehexers.eu.domain.workflow.main.MainState
import mobilehexers.eu.domain.workflow.main.MainWorkflow
import mobilehexers.eu.uibase.base.android.BaseActivity
import mobilehexers.eu.uibase.extensions.logTag
import javax.inject.Inject

class MainActivity : BaseActivity(), HasSupportFragmentInjector {

    @Inject lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>
    @Inject lateinit var workflow: MainWorkflow

    override fun getWorkflowInstance(): Workflow = workflow

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = fragmentInjector

    override fun handleStateChange(state: State) {
        if (state is MainState) {
            Log.d(logTag, "handleStateChange: " + state)
            when (state.currentState) {
                MainEnum.MAIN -> switchFragment(MainFragment.newInstance())
                MainEnum.ENDED -> finish()
                else -> Log.w(logTag, "Unsupported state: " + state)
            }
        } else {
            Log.w(logTag, "Wrong state type: " + state::class)
        }
    }
}

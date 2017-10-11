package mobilehexers.eu.driversweek.main

import android.os.Bundle
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
import mobilehexers.eu.driversweek.R
import mobilehexers.eu.driversweek.base.android.BaseActivity
import mobilehexers.eu.driversweek.extensions.logTag
import javax.inject.Inject

class MainActivity : BaseActivity(), HasSupportFragmentInjector {
    override fun getWorkflowInstance(): Workflow = workflow

    @Inject
    lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>

    @Inject
    lateinit var workflow: MainWorkflow

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = fragmentInjector

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(logTag, "onCreate")
    }

    override fun onResume() {
        super.onResume()
        Log.d(logTag, "onResume")
        workflow.next()
    }

    override fun handleStateChange(state: State) {
        Log.d(logTag, "handleStateChange")
        Log.d(logTag, workflow.toString())
        when ((state as MainState).currentState) {
            MainEnum.MAIN -> switchFragmentToMain()
            MainEnum.ENDED -> finish()
        }
    }

    private fun switchFragmentToMain() {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.base_container, MainFragment.newInstance())
//        if (addToBackStack) {
//            fragmentTransaction.addToBackStack(tag)
//        }
        fragmentTransaction.commit()
    }

}

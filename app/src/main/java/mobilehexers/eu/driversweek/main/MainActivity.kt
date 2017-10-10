package mobilehexers.eu.driversweek.main

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Action
import io.reactivex.functions.Consumer
import mobilehexers.eu.domain.workflow.main.MainEnum
import mobilehexers.eu.domain.workflow.main.MainState
import mobilehexers.eu.domain.workflow.main.MainWorkflow
import mobilehexers.eu.driversweek.R
import mobilehexers.eu.driversweek.base.android.BaseActivity
import mobilehexers.eu.driversweek.extensions.logTag
import javax.inject.Inject

class MainActivity : BaseActivity(), HasSupportFragmentInjector {

    @Inject
    lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>

    @Inject
    lateinit var workflow: MainWorkflow
    private var disposable: Disposable? = null

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = fragmentInjector

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(logTag, "onCreate")
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
        if (savedInstanceState == null) {
            disposable = workflow.init(Consumer { state -> handleStateChange(state as MainState) }, Consumer { }, Action { disposable?.dispose() })
        }
    }

    override fun onResume() {
        super.onResume()
        Log.d(logTag, "onResume")
        workflow.next()
    }

    private fun handleStateChange(state: MainState) {
        Log.d(logTag, "handleStateChange")
        Log.d(logTag, workflow.toString())
        when (state.currentState) {
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
package mobilehexers.eu.driversweek.main

import android.os.Bundle
import android.util.Log
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Action
import io.reactivex.functions.Consumer
import mobilehexers.eu.domain.workflow.main.MainEnum
import mobilehexers.eu.domain.workflow.main.MainState
import mobilehexers.eu.domain.workflow.main.MainWorkflow
import mobilehexers.eu.driversweek.R
import mobilehexers.eu.driversweek.base.android.BaseActivity
import mobilehexers.eu.driversweek.base.extensions.baseApplication
import mobilehexers.eu.driversweek.extensions.logTag
import javax.inject.Inject

class MainActivity : BaseActivity() {
    @Inject
    lateinit var workflow: MainWorkflow
    private var disposable: Disposable? = null
    private val applicationComponent by lazy { baseApplication.applicationComponent }

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(logTag, "onCreate")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
        baseApplication.plusMainComponent()
        applicationComponent.inject(this)
        if (savedInstanceState == null) {
            disposable = workflow.init(Consumer { state -> handleStateChange(state as MainState) }, Consumer { }, Action { disposable?.dispose() })
        }
    }

    override fun onResume() {
        super.onResume()
        Log.d(logTag, "onResume")
        workflow.next()
    }

    override fun onDestroy() {
        super.onDestroy()
        baseApplication.clearMainComponent()
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
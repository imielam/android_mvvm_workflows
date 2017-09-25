package mobilehexers.eu.driversweek.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Action
import io.reactivex.functions.Consumer
import mobilehexers.eu.domain.workflow.MainEnum
import mobilehexers.eu.domain.workflow.MainState
import mobilehexers.eu.domain.workflow.Workflow
import mobilehexers.eu.driversweek.R
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    @Inject private lateinit var workflow: Workflow
    private lateinit var container: View
    private var disposable: Disposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
        initView()
        if (savedInstanceState != null) {
            disposable = workflow.init(Consumer { state -> handleStateChange(state as MainState) }, Consumer { }, Action { disposable?.dispose() })
            workflow.next()
        }
    }

    private fun initView() {
        container = findViewById(R.id.base_container)
    }

    private fun handleStateChange(state: MainState) {
        when (state.currentState) {
            MainEnum.MAIN -> switchFragmentToMain()
            MainEnum.ENDED -> finish()
        }
    }

    private fun switchFragmentToMain() {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.base_container, MainFragment())
//        if (addToBackStack) {
//            fragmentTransaction.addToBackStack(tag)
//        }
        fragmentTransaction.commit()
    }

}
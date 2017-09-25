package mobilehexers.eu.driversweek.start

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Action
import io.reactivex.functions.Consumer
import mobilehexers.eu.domain.workflow.StartEnum
import mobilehexers.eu.domain.workflow.StartState
import mobilehexers.eu.domain.workflow.Workflow
import mobilehexers.eu.driversweek.R
import mobilehexers.eu.driversweek.main.MainActivity
import javax.inject.Inject

class StartActivity : AppCompatActivity() {
    @Inject private lateinit var workflow: Workflow
    private var disposable: Disposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
        if (savedInstanceState != null) {
            disposable = workflow.init(Consumer { state -> handleStateChange(state as StartState) }, Consumer { }, Action { disposable?.dispose() })
            workflow.next()
        }
    }

    private fun handleStateChange(state: StartState) {
        when (state.currentState) {
            StartEnum.ENDED ->  startMainActivity()
        }
    }

    private fun startMainActivity() {
        val newIntent = Intent(baseContext, MainActivity::class.java)
        startActivity(newIntent)
    }
}

package mobilehexers.eu.demo.main.activity

import android.support.v4.app.Fragment
import android.util.Log
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import mobilehexers.eu.domain.base.workflow.State
import mobilehexers.eu.domain.base.workflow.Workflow
import mobilehexers.eu.domain.extensions.logTag
import mobilehexers.eu.demo.main.list.MainListFragment
import mobilehexers.eu.demo.repository.activity.RepositoryActivity
import mobilehexers.eu.demo.weather.activity.WeatherActivity
import mobilehexers.eu.presentation.main.workflow.MainEnum
import mobilehexers.eu.presentation.main.workflow.MainState
import mobilehexers.eu.presentation.main.workflow.MainWorkflow
import mobilehexers.eu.uibase.base.android.BaseActivity
import javax.inject.Inject

class MainActivity : BaseActivity(), HasSupportFragmentInjector {

    @Inject
    lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>
    @Inject
    lateinit var workflow: MainWorkflow

    override fun getWorkflowInstance(): Workflow = workflow

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = fragmentInjector

    override fun handleStateChange(state: State) {
        if (state is MainState) {
            Log.d(logTag, "handleStateChange: " + state)
            when (state.currentState) {
                MainEnum.INITIALIZED -> workflow.next()
                MainEnum.MAIN -> switchFragment(MainListFragment.newInstance())
                MainEnum.REPOSITORY -> switchToRepositoryScreen()
                MainEnum.WEATHER -> switchToWeatherScreen()
                MainEnum.ENDED -> finishWorkflow()
                else -> Log.w(logTag, "Unsupported state: " + state)
            }
        } else {
            Log.w(logTag, "Wrong state type: " + state::class)
        }
    }

    private fun switchToRepositoryScreen() {
        startActivity(RepositoryActivity::class)
        workflow.end()
    }

    private fun switchToWeatherScreen() {
        startActivity(WeatherActivity::class)
        workflow.end()
    }

    override fun finishWorkflow() {
        workflow.end()
        finish()
    }
}

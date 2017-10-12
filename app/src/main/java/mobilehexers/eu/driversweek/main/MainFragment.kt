package mobilehexers.eu.driversweek.main

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_main.main_container_applause
import mobilehexers.eu.domain.workflow.main.MainEnum
import mobilehexers.eu.domain.workflow.main.MainState
import mobilehexers.eu.domain.workflow.main.MainWorkflow
import mobilehexers.eu.driversweek.R
import mobilehexers.eu.driversweek.extensions.inflate
import javax.inject.Inject

class MainFragment : Fragment() {

    @Inject lateinit var mainWorkflow: MainWorkflow

    companion object {
        fun newInstance(): MainFragment {
            val fragment = MainFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? = container?.inflate(R.layout.fragment_main)

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        main_container_applause.setOnClickListener({ openApplauseWorkflow() })
    }

    private fun openApplauseWorkflow() {
        mainWorkflow.next(MainState(MainEnum.REPOSITORY))
    }
}

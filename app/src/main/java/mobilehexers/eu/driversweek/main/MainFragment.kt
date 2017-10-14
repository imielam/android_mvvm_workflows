package mobilehexers.eu.driversweek.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_main.main_repository_list
import mobilehexers.eu.domain.workflow.main.MainEnum
import mobilehexers.eu.domain.workflow.main.MainState
import mobilehexers.eu.domain.workflow.main.MainWorkflow
import mobilehexers.eu.driversweek.R
import mobilehexers.eu.driversweek.extensions.inflate
import mobilehexers.eu.uibase.base.android.BaseFragment
import javax.inject.Inject

class MainFragment : BaseFragment() {

    @Inject lateinit var mainWorkflow: MainWorkflow

    private val repositoryListButton by lazy { main_repository_list }

    companion object {
        fun newInstance(): MainFragment {
            val fragment = MainFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? = container?.inflate(R.layout.fragment_main)

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        repositoryListButton.setOnClickListener({ showRepositoryList() })
    }

    private fun showRepositoryList() {
        mainWorkflow.next(MainState(MainEnum.REPOSITORY))
    }
}

/*
 * Copyright (c) 2017.  All rights reserved - Maciej Imiela.
 */

package mobilehexers.eu.driversweek.repository.list

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_repository_list.*
import mobilehexers.eu.driversweek.R
import mobilehexers.eu.driversweek.extensions.inflate
import mobilehexers.eu.presentation.repository.workflow.RepositoryWorkflow
import javax.inject.Inject

class RepositoryListFragment : Fragment() {

    @Inject lateinit var workflow: RepositoryWorkflow
    private val detailButton by lazy { repository_list_details_button }
    private val listView by lazy { repository_list_view }

    companion object {
        fun newInstance(): RepositoryListFragment {
            val fragment = RepositoryListFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? = container?.inflate(
            R.layout.fragment_repository_list)

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        detailButton.setOnClickListener({showDetails()})
        listView.setHasFixedSize(true)
        listView.layoutManager = LinearLayoutManager(context)
    }

    private fun showDetails() {
        workflow.next()
    }
}

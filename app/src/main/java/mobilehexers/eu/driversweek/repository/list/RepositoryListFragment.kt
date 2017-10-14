/*
 * Copyright (c) 2017.  All rights reserved - Maciej Imiela.
 */

package mobilehexers.eu.driversweek.repository.list

import android.content.Context
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.android.support.AndroidSupportInjection
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.fragment_repository_list.repository_list_container
import kotlinx.android.synthetic.main.fragment_repository_list.repository_list_details_button
import kotlinx.android.synthetic.main.fragment_repository_list.repository_list_filter_text
import kotlinx.android.synthetic.main.fragment_repository_list.repository_list_limit
import kotlinx.android.synthetic.main.fragment_repository_list.repository_list_view
import mobilehexers.eu.driversweek.R
import mobilehexers.eu.driversweek.extensions.inflate
import mobilehexers.eu.driversweek.extensions.logTag
import mobilehexers.eu.driversweek.repository.manager.RepositoryManager
import mobilehexers.eu.presentation.repository.workflow.RepositoryWorkflow
import javax.inject.Inject

class RepositoryListFragment : Fragment() {

    @Inject lateinit var workflow: RepositoryWorkflow
    @Inject lateinit var repositoryManager: RepositoryManager

    private val disposables: CompositeDisposable = CompositeDisposable()
    private val detailButton by lazy { repository_list_details_button }
    private val listView by lazy { repository_list_view }
    private val limitCheckBox by lazy { repository_list_limit }
    private val filterTextView by lazy { repository_list_filter_text }

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

    override fun onDetach() {
        super.onDetach()
        disposeSubscriptions()
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? = container?.inflate(
            R.layout.fragment_repository_list)

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initAdapter()
        addDisposable(repositoryManager.getRepositoryList().subscribe({ next -> (listView.adapter as RepositoryListAdapter).addRepositories(next) },
                { e -> Snackbar.make(repository_list_container, e.localizedMessage, Snackbar.LENGTH_SHORT).show() }))
    }

    private fun initView() {
        detailButton.setOnClickListener({ showDetails() })
        limitCheckBox.setOnClickListener({ limitRepositories(limitCheckBox.isChecked) })
        listView.setHasFixedSize(true)
        listView.layoutManager = LinearLayoutManager(context)
    }

    private fun limitRepositories(selected: Boolean) {
        Log.v(logTag, "Checkbox clicked. Selection: " + selected)
        val adapter = listView.adapter as RepositoryListAdapter
        if (selected) {
            adapter.limitRepositoriesTo(20)
        } else {
            adapter.removeLimit()
        }
    }

    private fun initAdapter() {
        val repositoryListAdapter = RepositoryListAdapter()
        listView.adapter = repositoryListAdapter
    }

    private fun showDetails() {
        workflow.next()
    }

    private fun addDisposable(disposable: Disposable) {
        disposables.add(disposable)
    }

    private fun disposeSubscriptions() {
        disposables.clear()
    }
}

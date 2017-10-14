/*
 * Copyright (c) 2017.  All rights reserved - Maciej Imiela.
 */

package mobilehexers.eu.driversweek.repository.list

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jakewharton.rxbinding2.widget.RxTextView
import kotlinx.android.synthetic.main.fragment_repository_list.repository_list_container
import kotlinx.android.synthetic.main.fragment_repository_list.repository_list_filter_text
import kotlinx.android.synthetic.main.fragment_repository_list.repository_list_limit
import kotlinx.android.synthetic.main.fragment_repository_list.repository_list_view
import mobilehexers.eu.domain.recycler.ViewType
import mobilehexers.eu.driversweek.R
import mobilehexers.eu.driversweek.extensions.inflate
import mobilehexers.eu.driversweek.extensions.logTag
import mobilehexers.eu.driversweek.repository.manager.RepositoryManager
import mobilehexers.eu.driversweek.repository.model.RepositoryModel
import mobilehexers.eu.presentation.repository.workflow.RepositoryWorkflow
import mobilehexers.eu.uibase.base.android.BaseFragment
import mobilehexers.eu.uibase.base.recycler.RecyclerViewOnItemClickListener
import javax.inject.Inject

class RepositoryListFragment : BaseFragment() {

    @Inject lateinit var workflow: RepositoryWorkflow
    @Inject lateinit var repositoryManager: RepositoryManager
    @Inject lateinit var model: RepositoryModel

    private val listView by lazy { repository_list_view }
    private val listAdapter: RepositoryListAdapter by lazy { listView.adapter as RepositoryListAdapter }
    private val limitCheckBox by lazy { repository_list_limit }
    private val filterTextView by lazy { repository_list_filter_text }

    private val defaultRepositoryLimit = 20

    private val listener = object : RecyclerViewOnItemClickListener {
        override fun onItemClick(item: ViewType) {
            if (item is RepositoryListItem) {
                model.repositoryItemClicked = item
                showDetails()
            }
        }
    }

    companion object {
        fun newInstance(): RepositoryListFragment {
            val fragment = RepositoryListFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? = container?.inflate(
            R.layout.fragment_repository_list)

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        addDisposable(repositoryManager.getRepositoryList().subscribe({ next -> listAdapter.addRepositories(next) },
                { e -> Snackbar.make(repository_list_container, e.localizedMessage, Snackbar.LENGTH_SHORT).show() }))
    }

    private fun initView() {
        initRecyclerView()

        limitCheckBox.setOnClickListener({ limitRepositories(limitCheckBox.isChecked) })

        initFilterTextView()
    }

    private fun initRecyclerView() {
        listView.setHasFixedSize(true)
        listView.layoutManager = LinearLayoutManager(context)
        listView.adapter = RepositoryListAdapter(listener)
    }

    private fun initFilterTextView() {
        addDisposable(RxTextView.textChanges(filterTextView).filter { text -> text.isNotBlank() }.subscribe({ text -> filterRepositoryList(text.toString()) }))
        addDisposable(RxTextView.textChanges(filterTextView).filter { text -> text.isBlank() }.subscribe({ _ -> removeFilterFromRepositoryList() }))
    }

    private fun limitRepositories(selected: Boolean) {
        Log.v(logTag, "Checkbox clicked. Selection: " + selected)
        if (selected) {
            listAdapter.limitRepositoriesTo(defaultRepositoryLimit)
        } else {
            listAdapter.removeLimit()
        }
    }

    private fun removeFilterFromRepositoryList() {
        Log.v(logTag, "Text empty. Remove filter")
        listAdapter.removeFilter()
    }

    private fun filterRepositoryList(text: String) {
        Log.v(logTag, "Text changed. Current text: " + text)
        listAdapter.filterRepositories(text)
    }

    private fun showDetails() {
        workflow.next()
    }
}

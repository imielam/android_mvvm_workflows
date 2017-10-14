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
import com.jakewharton.rxbinding2.widget.RxTextView
import dagger.android.support.AndroidSupportInjection
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
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
import mobilehexers.eu.uibase.base.recycler.RecyclerViewOnItemClickListener
import javax.inject.Inject

class RepositoryListFragment : Fragment() {

    @Inject lateinit var workflow: RepositoryWorkflow
    @Inject lateinit var repositoryManager: RepositoryManager
    @Inject lateinit var model: RepositoryModel

    private val disposables: CompositeDisposable = CompositeDisposable()
    private val listView by lazy { repository_list_view }
    private val limitCheckBox by lazy { repository_list_limit }
    private val filterTextView by lazy { repository_list_filter_text }

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
        limitCheckBox.setOnClickListener({ limitRepositories(limitCheckBox.isChecked) })
        addDisposable(RxTextView.textChanges(filterTextView).filter { text -> text.isNotBlank() }.subscribe({ text -> filterRepositoryList(text.toString()) }))
        addDisposable(RxTextView.textChanges(filterTextView).filter { text -> text.isBlank() }.subscribe({ _ -> removeFilterFromRepositoryList() }))
        listView.setHasFixedSize(true)
        listView.layoutManager = LinearLayoutManager(context)
    }

    private fun removeFilterFromRepositoryList() {
        if (listView.adapter is RepositoryListAdapter) {
            val adapter = listView.adapter as RepositoryListAdapter
            adapter.removeFilter()
        }
    }

    private fun filterRepositoryList(text: String) {
        Log.v(logTag, "Text changed. Current text: " + text)
        val adapter = listView.adapter as RepositoryListAdapter
        adapter.filterRepositories(text)
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
        val repositoryListAdapter = RepositoryListAdapter(listener)
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
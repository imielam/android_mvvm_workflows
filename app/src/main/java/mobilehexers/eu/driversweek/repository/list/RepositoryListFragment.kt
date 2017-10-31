/*
 * Copyright (c) 2017.  All rights reserved - Maciej Imiela.
 */

package mobilehexers.eu.driversweek.repository.list

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jakewharton.rxbinding2.view.RxView
import com.jakewharton.rxbinding2.widget.RxTextView
import io.reactivex.Observable
import kotlinx.android.synthetic.main.fragment_repository_list.repository_list_filter_text
import kotlinx.android.synthetic.main.fragment_repository_list.repository_list_limit
import kotlinx.android.synthetic.main.fragment_repository_list.repository_list_view
import mobilehexers.eu.domain.base.recycler.ViewType
import mobilehexers.eu.domain.base.viewmodel.FragmentViewModel
import mobilehexers.eu.domain.repository.list.entity.RepositoryListItem
import mobilehexers.eu.driversweek.databinding.FragmentRepositoryListBinding
import mobilehexers.eu.uibase.base.android.BaseFragment
import mobilehexers.eu.uibase.base.recycler.RecyclerViewOnItemClickListener
import javax.inject.Inject

class RepositoryListFragment : BaseFragment() {

    @Inject lateinit override var viewModel: FragmentViewModel

    private val editTextObservable: Observable<String> by lazy { RxTextView.textChanges(repository_list_filter_text).map { data -> data.toString() } }
    private val checkBoxObservable: Observable<Boolean> by lazy { RxView.clicks(repository_list_limit).map { repository_list_limit.isChecked } }

    private val listView by lazy { repository_list_view }
    private val listAdapter: RepositoryListAdapter by lazy { listView.adapter as RepositoryListAdapter }

    private val listener = object : RecyclerViewOnItemClickListener {
        override fun onItemClick(item: ViewType) {
            if (item is RepositoryListItem) {
                (viewModel as RepositoryListViewModel).repositoryItemClicked(item)
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

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentRepositoryListBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel as RepositoryListViewModel
        return binding.root
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        initViewModel()
    }

    private fun initViewModel() {
        val repositoryListViewModel = viewModel as RepositoryListViewModel
        repositoryListViewModel.listAdapter = listAdapter
        repositoryListViewModel.filterObservable = editTextObservable
        repositoryListViewModel.limitObservable = checkBoxObservable
    }

    private fun initRecyclerView() {
        listView.setHasFixedSize(true)
        listView.layoutManager = LinearLayoutManager(context)
        listView.adapter = RepositoryListAdapter(listener)
    }
}

/*
 * Copyright (c) 2017.  All rights reserved - Maciej Imiela.
 */

package mobilehexers.eu.driversweek.repository.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_repository_detail.repository_details_item_container
import kotlinx.android.synthetic.main.fragment_repository_detail.repository_details_progressbar_container
import mobilehexers.eu.driversweek.databinding.FragmentRepositoryDetailBinding
import mobilehexers.eu.driversweek.repository.manager.RepositoryManager
import mobilehexers.eu.driversweek.repository.model.RepositoryModel
import mobilehexers.eu.presentation.repository.workflow.RepositoryWorkflow
import mobilehexers.eu.uibase.base.android.BaseFragment
import javax.inject.Inject

class RepositoryDetailsFragment : BaseFragment() {

    @Inject lateinit var workflow: RepositoryWorkflow
    @Inject lateinit var repositoryManager: RepositoryManager
    @Inject lateinit var model: RepositoryModel

    private val details = RepositoryDetailsItem()
    private lateinit var binding: FragmentRepositoryDetailBinding

    companion object {
        fun newInstance(): RepositoryDetailsFragment {
            val fragment = RepositoryDetailsFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentRepositoryDetailBinding.inflate(inflater)
        binding.details = details
        return binding.root
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addDisposable(repositoryManager.getRepositoryDetail(repositoryName = model.repositoryItemClicked.name).subscribe(
                { next -> updateViewWith(next); showDataAndHideProgress() }))
    }

    private fun updateViewWith(item: RepositoryDetailsItem) {
        binding.details = item
    }

    private fun showDataAndHideProgress() {
        repository_details_progressbar_container.visibility = View.GONE
        repository_details_item_container.visibility = View.VISIBLE
    }
}
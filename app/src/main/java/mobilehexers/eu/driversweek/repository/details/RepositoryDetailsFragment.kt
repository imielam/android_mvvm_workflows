/*
 * Copyright (c) 2017.  All rights reserved - Maciej Imiela.
 */

package mobilehexers.eu.driversweek.repository.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_repository_detail.repository_details_description_text
import kotlinx.android.synthetic.main.fragment_repository_detail.repository_details_id_text
import kotlinx.android.synthetic.main.fragment_repository_detail.repository_details_item_container
import kotlinx.android.synthetic.main.fragment_repository_detail.repository_details_language_text
import kotlinx.android.synthetic.main.fragment_repository_detail.repository_details_name_text
import kotlinx.android.synthetic.main.fragment_repository_detail.repository_details_progressbar_container
import mobilehexers.eu.driversweek.R
import mobilehexers.eu.driversweek.extensions.inflate
import mobilehexers.eu.driversweek.repository.manager.RepositoryManager
import mobilehexers.eu.driversweek.repository.model.RepositoryModel
import mobilehexers.eu.presentation.repository.workflow.RepositoryWorkflow
import mobilehexers.eu.uibase.base.android.BaseFragment
import javax.inject.Inject

class RepositoryDetailsFragment : BaseFragment() {

    @Inject lateinit var workflow: RepositoryWorkflow
    @Inject lateinit var repositoryManager: RepositoryManager
    @Inject lateinit var model: RepositoryModel

    private val idTextView by lazy { repository_details_id_text }
    private val nameTextView by lazy { repository_details_name_text }
    private val descriptionTextView by lazy { repository_details_description_text }
    private val languageTextView by lazy { repository_details_language_text }

    companion object {
        fun newInstance(): RepositoryDetailsFragment {
            val fragment = RepositoryDetailsFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? = container?.inflate(
            R.layout.fragment_repository_detail)

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        addDisposable(repositoryManager.getRepositoryDetail(repositoryName = model.repositoryItemClicked.name).subscribe(
                { next -> updateViewWith(next); showDataAndHideProgress() }))
    }

    private fun updateViewWith(item: RepositoryDetailsItem) {
        idTextView.text = item.id
        nameTextView.text = item.name
        descriptionTextView.text = item.description
        languageTextView.text = item.language
    }

    private fun showDataAndHideProgress() {
        repository_details_progressbar_container.visibility = View.GONE
        repository_details_item_container.visibility = View.VISIBLE
    }
}
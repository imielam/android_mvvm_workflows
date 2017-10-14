/*
 * Copyright (c) 2017.  All rights reserved - Maciej Imiela.
 */

package mobilehexers.eu.driversweek.repository.details

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_repository_detail.repository_details_description_text
import kotlinx.android.synthetic.main.fragment_repository_detail.repository_details_id_text
import kotlinx.android.synthetic.main.fragment_repository_detail.repository_details_language_text
import kotlinx.android.synthetic.main.fragment_repository_detail.repository_details_name_text
import mobilehexers.eu.driversweek.R
import mobilehexers.eu.driversweek.extensions.inflate
import mobilehexers.eu.presentation.repository.workflow.RepositoryWorkflow
import javax.inject.Inject

class RepositoryDetailsFragment : Fragment() {

    @Inject lateinit var workflow: RepositoryWorkflow
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

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? = container?.inflate(
            R.layout.fragment_repository_detail)

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        val item = createMockedRepositoryDetailsItem()
        idTextView.text=item.id
        nameTextView.text = item.name
        descriptionTextView.text = item.description
        languageTextView.text = item.language
    }

    private fun createMockedRepositoryDetailsItem() = RepositoryDetailsItem("01", "retrofit", "Type-safe HTTP client for Android and Java by Square, Inc.", "Java")
}
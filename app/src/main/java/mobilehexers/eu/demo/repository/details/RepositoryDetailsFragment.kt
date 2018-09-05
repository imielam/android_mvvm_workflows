/*
 * Copyright (c) 2017.  All rights reserved - Maciej Imiela.
 */

package mobilehexers.eu.demo.repository.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import mobilehexers.eu.domain.base.viewmodel.FragmentViewModel
import mobilehexers.eu.demo.databinding.FragmentRepositoryDetailBinding
import mobilehexers.eu.demo.repository.details.RepositoryDetailsFragment
import mobilehexers.eu.demo.repository.details.RepositoryDetailsViewModel
import mobilehexers.eu.uibase.base.android.BaseFragment
import javax.inject.Inject

class RepositoryDetailsFragment : BaseFragment() {

    @Inject lateinit override var viewModel: FragmentViewModel

    companion object {
        fun newInstance(): RepositoryDetailsFragment {
            val fragment = RepositoryDetailsFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val detailBinding = FragmentRepositoryDetailBinding.inflate(inflater)
        detailBinding.viewModel = viewModel as RepositoryDetailsViewModel
        return detailBinding.root
    }
}
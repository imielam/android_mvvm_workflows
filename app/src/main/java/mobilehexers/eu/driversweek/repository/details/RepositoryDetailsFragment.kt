/*
 * Copyright (c) 2017.  All rights reserved - Maciej Imiela.
 */

package mobilehexers.eu.driversweek.repository.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import mobilehexers.eu.driversweek.databinding.FragmentRepositoryDetailBinding
import mobilehexers.eu.uibase.base.android.BaseFragment
import javax.inject.Inject

class RepositoryDetailsFragment : BaseFragment() {

    @Inject lateinit var viewModel: RepositoryDetailsViewModel

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
        val detailBinding = FragmentRepositoryDetailBinding.inflate(inflater)
        detailBinding.viewModel = viewModel
        return detailBinding.root
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.onViewCreated()
    }
}
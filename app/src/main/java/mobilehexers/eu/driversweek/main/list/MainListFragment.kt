/*
 * Copyright (c) 2017.  All rights reserved - Maciej Imiela.
 */

package mobilehexers.eu.driversweek.main.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import mobilehexers.eu.domain.base.viewmodel.FragmentViewModel
import mobilehexers.eu.driversweek.databinding.FragmentMainListBinding
import mobilehexers.eu.uibase.base.android.BaseFragment
import javax.inject.Inject

class MainListFragment : BaseFragment() {
    @Inject lateinit override var viewModel: FragmentViewModel

    companion object {
        fun newInstance(): MainListFragment {
            val fragment = MainListFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val binding = FragmentMainListBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel as MainListViewModel
        return binding.root
    }
}

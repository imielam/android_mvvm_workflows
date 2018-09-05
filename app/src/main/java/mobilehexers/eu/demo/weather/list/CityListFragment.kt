/*
 * Copyright (c) 2018.  All rights reserved - Maciej Imiela.
 */

package mobilehexers.eu.demo.weather.list

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jakewharton.rxbinding2.widget.RxTextView
import io.reactivex.BackpressureStrategy
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.fragment_wether_list.*
import kotlinx.android.synthetic.main.fragment_wether_list.view.*
import mobilehexers.eu.domain.base.viewmodel.FragmentViewModel
import mobilehexers.eu.domain.extensions.logTag
import mobilehexers.eu.domain.weather.list.entity.City
import mobilehexers.eu.domain.weather.model.WeatherModel
import mobilehexers.eu.demo.R
import mobilehexers.eu.demo.weather.list.item.OnItemClickListener
import mobilehexers.eu.uibase.base.android.BaseFragment
import javax.inject.Inject

class CityListFragment : BaseFragment() {

    @Inject
    override lateinit var viewModel: FragmentViewModel
    private val typedViewModel by lazy { viewModel as CityListViewModel }

    @Inject
    lateinit var model: WeatherModel

    private val disposable = CompositeDisposable()
    private val clickListener = object : OnItemClickListener {

        override fun onClick(cityItem: City) {
            val viewModel = this@CityListFragment.viewModel as CityListViewModel
            viewModel.itemClicked(cityItem)
        }
    }

    private lateinit var adapter: CityListAdapter

    private val editTextObservable: Observable<String> by lazy { RxTextView.textChanges(weather_list_filter_text).map { data -> data.toString() } }
    private lateinit var recyclerView: RecyclerView
    private val loadingIndicator by lazy { weather_list_loading_indicator }
    private val filterEditText by lazy { weather_list_filter_text }

    companion object {
        fun newInstance(): CityListFragment {
            val fragment = CityListFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_wether_list, container, false)
        recyclerView = rootView.weather_list_view
        recyclerView.layoutManager = LinearLayoutManager(this.context)
        recyclerView.setHasFixedSize(true)
        clearRecyclerView()
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initViewModel()
        super.onViewCreated(view, savedInstanceState)
    }

    private fun initViewModel() {
        disposable.add(
                typedViewModel.getFilteredData(editTextObservable.toFlowable(BackpressureStrategy.LATEST))
                        .subscribe(
                                {
                                    clearRecyclerView()
                                    adapter.submitList(it)
                                },
                                this::handleError
                        )
        )
    }

    private fun clearRecyclerView() {
        adapter = CityListAdapter(clickListener)
        recyclerView.adapter = adapter
    }

    private fun handleError(e: Throwable) {
        Log.e(logTag, e.localizedMessage)
    }

    override fun onStart() {
        super.onStart()
        disposable.add(
                typedViewModel.cities.subscribe {
                    loadingIndicator.visibility = View.GONE
                    recyclerView.visibility = View.VISIBLE
                    filterEditText.isEnabled = true
                    adapter.submitList(it)
                }
        )
    }

    override fun onStop() {
        super.onStop()
        disposable.clear()
    }

}

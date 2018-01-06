/*
 * Copyright (c) 2017.  All rights reserved - Maciej Imiela.
 */

package mobilehexers.eu.driversweek.courses.list

import android.util.Log
import me.tatarka.bindingcollectionadapter2.ItemBinding
import me.tatarka.bindingcollectionadapter2.collections.DiffObservableList
import mobilehexers.eu.domain.base.recycler.ViewType
import mobilehexers.eu.domain.base.viewmodel.FragmentViewModel
import mobilehexers.eu.domain.base.workflow.Workflow
import mobilehexers.eu.domain.courses.model.CoursesModel
import mobilehexers.eu.domain.extensions.logTag
import mobilehexers.eu.driversweek.BR
import mobilehexers.eu.driversweek.R
import mobilehexers.eu.driversweek.courses.list.items.CoursesListItem
import mobilehexers.eu.uibase.base.recycler.RecyclerViewOnItemClickListener
import javax.inject.Inject

class CoursesListViewModel @Inject constructor(private val workflow: Workflow, private val model: CoursesModel) : FragmentViewModel() {
    val items: DiffObservableList<CoursesListItem> = DiffObservableList(object : DiffObservableList.Callback<CoursesListItem> {
        override fun areContentsTheSame(oldItem: CoursesListItem, newItem: CoursesListItem) = oldItem == newItem

        override fun areItemsTheSame(oldItem: CoursesListItem, newItem: CoursesListItem) = oldItem == newItem
    })

    val itemBinding = ItemBinding.of<CoursesListItem>(BR.item, R.layout.item_courses_list).bindExtra(BR.listener,
            object : RecyclerViewOnItemClickListener {
                override fun onItemClick(item: ViewType) {
                    Log.d(logTag, "Clicked")
                }
            })


    override fun onViewCreated() {
        val map = model.getCourses().map { it.map { CoursesListItem(it.name, it.description) } }
        addDisposable(map.subscribe({ next -> addRepositories(next) }, { e -> handleError(e) }))
    }

    private fun addRepositories(courses: List<CoursesListItem>) {
        Log.v(logTag, "Add Courses: $courses")
        updateList(courses)
    }

    private fun updateList(data: List<CoursesListItem>) {
        val diffResult = items.calculateDiff(data)
        items.update(data, diffResult)
    }

    private fun handleError(e: Throwable) {
        Log.e(logTag, e.localizedMessage)
    }
}

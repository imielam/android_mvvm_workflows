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
import mobilehexers.eu.domain.courses.list.entity.CoursesListItem
import mobilehexers.eu.domain.extensions.logTag
import mobilehexers.eu.driversweek.BR
import mobilehexers.eu.driversweek.R
import mobilehexers.eu.uibase.base.recycler.RecyclerViewOnItemClickListener
import javax.inject.Inject

class CoursesListViewModel @Inject constructor(private val workflow: Workflow) : FragmentViewModel() {
    val items: DiffObservableList<CoursesListItem> = DiffObservableList(object : DiffObservableList.Callback<CoursesListItem> {
        override fun areContentsTheSame(oldItem: CoursesListItem, newItem: CoursesListItem) = oldItem.name == newItem.name

        override fun areItemsTheSame(oldItem: CoursesListItem, newItem: CoursesListItem) = oldItem == newItem
    })

    val itemBinding = ItemBinding.of<CoursesListItem>(BR.item, R.layout.item_courses_list).bindExtra(BR.listener,
            object : RecyclerViewOnItemClickListener {
                override fun onItemClick(item: ViewType) {
                    Log.d(logTag, "Clicked")
                }
            })


    override fun onViewCreated() {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}

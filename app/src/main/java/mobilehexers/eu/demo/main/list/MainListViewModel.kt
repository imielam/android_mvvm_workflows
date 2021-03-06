/*
 * Copyright (c) 2017.  All rights reserved - Maciej Imiela.
 */

package mobilehexers.eu.demo.main.list

import mobilehexers.eu.domain.base.viewmodel.FragmentViewModel
import mobilehexers.eu.domain.base.workflow.Workflow
import mobilehexers.eu.presentation.main.workflow.MainEnum
import mobilehexers.eu.presentation.main.workflow.MainState
import javax.inject.Inject

/**
 * Created by mimiela on 31.10.17.
 */
class MainListViewModel @Inject constructor(private val workflow: Workflow) : FragmentViewModel() {

    override fun onViewCreated() {
    }

    fun showRepositoryList() {
        workflow.next(MainState(MainEnum.REPOSITORY))
    }

    fun showCoursesList() {
        workflow.next(MainState(MainEnum.COURSES))
    }

    fun showWeatherList() {
        workflow.next(MainState(MainEnum.WEATHER))
    }
}
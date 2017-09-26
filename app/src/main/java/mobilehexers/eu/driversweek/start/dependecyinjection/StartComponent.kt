/*
 * Copyright (c) 2017 MobileHexers.com ALL RIGHTS RESERVED
 */

package com.mobilehexers.driversweek.main.dependecyinjection

import dagger.Subcomponent
import mobilehexers.eu.driversweek.start.StartActivity
import javax.inject.Singleton

/**
 * Created by maciej.imiela on 20.07.2017.
 */
//@Singleton
@Subcomponent(modules = arrayOf(StartModule::class))
interface StartComponent {
    fun inject(activity: StartActivity)

//    val startWorkflow: Workflow
}
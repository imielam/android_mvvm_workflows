/*
 * Copyright (c) 2017 MobileHexers.com ALL RIGHTS RESERVED
 */

package com.mobilehexers.driversweek.main.dependecyinjection

import dagger.Subcomponent
import mobilehexers.eu.driversweek.main.MainActivity
import javax.inject.Singleton

/**
 * Created by maciej.imiela on 20.07.2017.
 */
//@Singleton
@Subcomponent(modules = arrayOf(MainModule::class))
interface MainComponent {
    fun inject(activity: MainActivity)

//    val mainWorkflow: Workflow
}
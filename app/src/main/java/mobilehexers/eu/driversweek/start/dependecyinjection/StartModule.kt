/*
 * Copyright (c) 2017 MobileHexers.com ALL RIGHTS RESERVED
 */

package com.mobilehexers.driversweek.main.dependecyinjection

import dagger.Module
import mobilehexers.eu.driversweek.start.StartActivity

/**
 * Created by maciej.imiela on 20.07.2017.
 */
@Module
class StartModule(val activity: StartActivity) {

//    @Provides
//    @Singleton
//    fun provideStartWorkflow() = Workflow(StartState())
}
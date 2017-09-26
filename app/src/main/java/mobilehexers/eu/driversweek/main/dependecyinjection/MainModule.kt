/*
 * Copyright (c) 2017 MobileHexers.com ALL RIGHTS RESERVED
 */

package com.mobilehexers.driversweek.main.dependecyinjection

import dagger.Module
import mobilehexers.eu.driversweek.main.MainActivity

/**
 * Created by maciej.imiela on 20.07.2017.
 */
@Module
class MainModule(val activity: MainActivity) {

//    @Provides
//    @Singleton
//    fun provideMainWorkflow() = Workflow(MainState())
}
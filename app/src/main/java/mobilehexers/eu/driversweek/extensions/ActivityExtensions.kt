/*
 * Copyright (c) 2017 MobileHexers.com ALL RIGHTS RESERVED
 */

package mobilehexers.eu.driversweek.base.extensions

import android.app.Activity
import mobilehexers.eu.driversweek.base.BaseApplication

/**
 * Created by maciej.imiela on 20.07.2017.
 */

val Activity.baseApplication: BaseApplication
    get() = application as BaseApplication
//val Any.logTag: String
//    get() = javaClass.simpleName
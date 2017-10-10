/*
 * Copyright (c) 2017 MobileHexers.com ALL RIGHTS RESERVED
 */

package mobilehexers.eu.driversweek.base.extensions

import android.app.Activity
import android.support.v4.app.Fragment
import mobilehexers.eu.driversweek.base.android.BaseActivity
import mobilehexers.eu.driversweek.base.android.BaseApplication

/**
 * Created by maciej.imiela on 20.07.2017.
 */

val Activity.baseApplication: BaseApplication
    get() = application as BaseApplication

val Fragment.baseActivity: BaseActivity
    get() = activity as BaseActivity
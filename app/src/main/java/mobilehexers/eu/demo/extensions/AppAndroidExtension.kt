/*
 * Copyright (c) 2017.  All rights reserved - Maciej Imiela.
 */

package mobilehexers.eu.demo.extensions

import android.app.Activity
import mobilehexers.eu.demo.base.android.BaseApplication

val Activity.baseApplication: BaseApplication
    get() = application as BaseApplication
/*
 * Copyright (c) 2017.  All rights reserved - Maciej Imiela.
 */

package mobilehexers.eu.driversweek.extensions

import android.app.Activity
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import mobilehexers.eu.driversweek.base.android.BaseApplication
import mobilehexers.eu.uibase.base.android.BaseActivity

/**
 * Created by maciej.imiela on 20.07.2017.
 */

val Activity.baseApplication: BaseApplication
    get() = application as BaseApplication

val Fragment.baseActivity: BaseActivity
    get() = activity as BaseActivity

fun ViewGroup.inflate(layoutId: Int, attachToRoot: Boolean = false): View = LayoutInflater.from(context).inflate(layoutId, this, attachToRoot)
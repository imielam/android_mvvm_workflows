/*
 * Copyright (c) 2017. All rights reserved - Maciej Imiela.
 */

package mobilehexers.eu.uibase.extensions

import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import mobilehexers.eu.uibase.base.android.BaseActivity

val Fragment.baseActivity: BaseActivity
    get() = activity as BaseActivity

fun ViewGroup.inflate(layoutId: Int, attachToRoot: Boolean = false): View = LayoutInflater.from(context).inflate(layoutId, this, attachToRoot)

/*
 * Copyright (c) 2018.  All rights reserved - Maciej Imiela.
 */

package mobilehexers.eu.domain.start

import io.reactivex.Completable

interface StartModel {
    fun initDataBase(): Completable
}
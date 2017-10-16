/*
 * Copyright (c) 2017.  All rights reserved - Maciej Imiela.
 */

package mobilehexers.eu.domain.base.model

import mobilehexers.eu.domain.base.recycler.ViewType

interface ApplicationDataSet {
    fun updateWith(data: List<ViewType>)
}
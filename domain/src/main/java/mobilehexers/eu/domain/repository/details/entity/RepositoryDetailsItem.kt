/*
 * Copyright (c) 2017.  All rights reserved - Maciej Imiela.
 */

package mobilehexers.eu.domain.repository.details.entity

data class RepositoryDetailsItem(val id: String, val name: String, val description: String?, val language: String?) {
    constructor() : this("", "", "", "")
}
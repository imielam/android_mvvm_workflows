/*
 * Copyright (c) 2018.  All rights reserved - Maciej Imiela.
 */

package mobilehexers.eu.domain.start

import io.reactivex.Completable
import mobilehexers.eu.domain.base.rx.SchedulerProvider
import mobilehexers.eu.domain.weather.repository.CityRepository

class StartModelImpl(private val cityRepository: CityRepository, private val schedulerProvider: SchedulerProvider) : StartModel {
    override fun initDataBase(): Completable =
            cityRepository.initiated
                    .flatMapCompletable {
                        if (it) {
                            Completable.fromAction { }
                        } else {
                            cityRepository.initDataBase()
                        }
                    }
}

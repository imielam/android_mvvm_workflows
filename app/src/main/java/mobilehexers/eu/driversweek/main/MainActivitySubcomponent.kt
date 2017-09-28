package mobilehexers.eu.driversweek.main

import dagger.Subcomponent
import dagger.android.AndroidInjector

/**
 * Created by maciej.imiela on 28.09.2017.
 */
@Subcomponent()
interface MainActivitySubcomponent : AndroidInjector<MainActivity> {
    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<MainActivity>()
}
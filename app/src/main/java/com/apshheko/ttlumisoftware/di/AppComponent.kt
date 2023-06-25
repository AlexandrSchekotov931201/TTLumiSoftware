package com.apshheko.ttlumisoftware.di

import com.apshheko.ttlumisoftware.ui.fragments.RootFragment
import com.apshheko.ttlumisoftware.ui.fragments.TransactionsFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        NetworkModule::class
    ]
)
interface AppComponent {
    fun inject(fragment: RootFragment)
    fun inject(fragment: TransactionsFragment)
}

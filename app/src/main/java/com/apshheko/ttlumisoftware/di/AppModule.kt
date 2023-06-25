package com.apshheko.ttlumisoftware.di

import com.apshheko.ttlumisoftware.network.repositories.impl.EtherscanRepositoryImpl
import com.apshheko.ttlumisoftware.network.repositories.interfaces.EtherscanRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class AppModule {

    @Singleton
    @Binds
    abstract fun provideEtherscanRepository(o: EtherscanRepositoryImpl): EtherscanRepository
}

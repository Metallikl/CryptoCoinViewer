package br.com.dluche.criptocoinviewer.di

import br.com.dluche.criptocoinviewer.common.AppCoroutinesDispatchers
import br.com.dluche.criptocoinviewer.common.AppCoroutinesDispatchersImpl
import br.com.dluche.criptocoinviewer.data.repository.CryptoCoinRepositoryImpl
import br.com.dluche.criptocoinviewer.domain.repository.CryptoCoinRepository
import br.com.dluche.criptocoinviewer.domain.usescases.*
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DomainModule {

    @Binds
    fun bindGetCoinsUseCase(getCoinsUseCase: GetCoinsUseCaseImpl): GetCoinsUseCase

    @Binds
    fun bindGetCoinDetailsUseCase(getCoinDetailsUseCase: GetCoinDetailsUseCaseImpl): GetCoinDetailsUseCase

    @Binds
    fun bindGetCoinNextPageUseCase(getCoinsNextPageUseCaseImpl: GetCoinsNextPageUseCaseImpl): GetCoinsNextPageUseCase

    @Binds
    fun bindCryptoCoinRepository(repository: CryptoCoinRepositoryImpl): CryptoCoinRepository

    @Binds
    fun bindDispatechers(appCoroutinesDispatchers: AppCoroutinesDispatchersImpl): AppCoroutinesDispatchers
}
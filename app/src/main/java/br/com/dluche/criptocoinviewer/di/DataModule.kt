package br.com.dluche.criptocoinviewer.di

import br.com.dluche.criptocoinviewer.data.datasource.CryptoCoinLocalDataSource
import br.com.dluche.criptocoinviewer.data.datasource.CryptoCoinLocalDataSourceImpl
import br.com.dluche.criptocoinviewer.data.datasource.CryptoCoinRemoteDataSource
import br.com.dluche.criptocoinviewer.data.datasource.CryptoCoinRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {
    @Binds
    fun bindRemoteDataSource(coinRemoteDataSource: CryptoCoinRemoteDataSourceImpl): CryptoCoinRemoteDataSource

    @Binds
    fun bindLocalDataSource(coinLocalDataSource: CryptoCoinLocalDataSourceImpl): CryptoCoinLocalDataSource
}
package br.com.dluche.criptocoinviewer.di

import br.com.dluche.criptocoinviewer.common.ContentProvider
import br.com.dluche.criptocoinviewer.common.ContentProviderImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface AppBindersModule {

    @Binds
    fun bindContentProvider(provider: ContentProviderImpl): ContentProvider
}
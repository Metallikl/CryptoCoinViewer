package br.com.dluche.criptocoinviewer.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import br.com.dluche.criptocoinviewer.common.Constants.DATABASE_NAME
import br.com.dluche.criptocoinviewer.data.database.CryptoCoinDb
import br.com.dluche.criptocoinviewer.data.database.dao.CryptoCoinDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    fun provideCoinsDao(cryptoCoinDb: CryptoCoinDb): CryptoCoinDao{
        return cryptoCoinDb.coinsDao()
    }

    @Provides
    @Singleton
    fun provideCryptoCoinBd(@ApplicationContext appContext: Context): CryptoCoinDb{
        return Room.databaseBuilder(
            appContext,
            CryptoCoinDb::class.java,
            DATABASE_NAME
        ).build()
    }
}
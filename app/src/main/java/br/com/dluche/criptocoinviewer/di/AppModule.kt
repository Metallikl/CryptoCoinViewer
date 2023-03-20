package br.com.dluche.criptocoinviewer.di

import br.com.dluche.criptocoinviewer.common.AppCoroutinesDispatchersImpl
import br.com.dluche.criptocoinviewer.data.api.PaprikaCoinApi
import br.com.dluche.criptocoinviewer.common.Constants.BASE_URL
import br.com.dluche.criptocoinviewer.data.mappers.CryptoCoinDtoDomainMapper
import br.com.dluche.criptocoinviewer.data.mappers.CryptoCoinDetailsDtoDomainMapper
import br.com.dluche.criptocoinviewer.data.mappers.CryptoCoinDtoToEntityMapper
import br.com.dluche.criptocoinviewer.data.mappers.CryptoCoinEntityDomainMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    fun providePaprikaApi(): PaprikaCoinApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PaprikaCoinApi::class.java)
    }

    @Provides
    fun provideDispatchersImpl(): AppCoroutinesDispatchersImpl {
        return AppCoroutinesDispatchersImpl()
    }

    @Provides
    fun provideCryptoCoinDtoDomainMapper(): CryptoCoinDtoDomainMapper {
        return CryptoCoinDtoDomainMapper()
    }

    @Provides
    fun provideCryptoCoinDetailsDtoDomainMapper(): CryptoCoinDetailsDtoDomainMapper {
        return CryptoCoinDetailsDtoDomainMapper()
    }

    @Provides
    fun provideCryptoCoinDtoToEntityMapper(): CryptoCoinDtoToEntityMapper {
        return CryptoCoinDtoToEntityMapper()
    }

    @Provides
    fun provideCryptoCoinEntityDomainMapper(): CryptoCoinEntityDomainMapper =
        CryptoCoinEntityDomainMapper()
}
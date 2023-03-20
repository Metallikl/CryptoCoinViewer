package br.com.dluche.criptocoinviewer.data.datasource

import br.com.dluche.criptocoinviewer.common.EitherResult
import br.com.dluche.criptocoinviewer.data.model.entity.CryptoCoinEntity

interface CryptoCoinLocalDataSource {

    suspend fun insertCoins(coins: List<CryptoCoinEntity>): EitherResult<Any>

    suspend fun getCoins(offset: Int, limit: Int = 20): EitherResult<List<CryptoCoinEntity>>

    suspend fun deleteCoins(): EitherResult<Any>

    suspend fun getCoinsNextPage(search: String?, offset: Int, limit: Int = 20): EitherResult<List<CryptoCoinEntity>>
}
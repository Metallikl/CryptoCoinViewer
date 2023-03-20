package br.com.dluche.criptocoinviewer.domain.repository

import br.com.dluche.criptocoinviewer.common.EitherResult
import br.com.dluche.criptocoinviewer.domain.model.CoinPaginationData
import br.com.dluche.criptocoinviewer.domain.model.CryptoCoin
import br.com.dluche.criptocoinviewer.domain.model.CryptoCoinDetails

interface CryptoCoinRepository {
    suspend fun getCoins(
        forceReset: Boolean = false,
        paginationData: CoinPaginationData = CoinPaginationData()
    ): EitherResult<List<CryptoCoin>>

    suspend fun getCoinDetails(coinId: String): EitherResult<CryptoCoinDetails>

    suspend fun getCoinsNextPage(paginationData: CoinPaginationData): EitherResult<List<CryptoCoin>>
}
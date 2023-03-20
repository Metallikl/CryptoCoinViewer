package br.com.dluche.criptocoinviewer.domain.usescases

import br.com.dluche.criptocoinviewer.common.EitherResult
import br.com.dluche.criptocoinviewer.domain.model.CoinPaginationData
import br.com.dluche.criptocoinviewer.domain.model.CryptoCoin
import kotlinx.coroutines.flow.Flow

interface GetCoinsUseCase {
    suspend operator fun invoke(forceReset: Boolean = false, paginationData: CoinPaginationData = CoinPaginationData()): Flow<EitherResult<List<CryptoCoin>>>
}
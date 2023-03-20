package br.com.dluche.criptocoinviewer.domain.usescases

import br.com.dluche.criptocoinviewer.common.EitherResult
import br.com.dluche.criptocoinviewer.domain.model.CoinPaginationData
import br.com.dluche.criptocoinviewer.domain.model.CryptoCoin
import kotlinx.coroutines.flow.Flow

interface GetCoinsNextPageUseCase {
    suspend operator fun invoke(paginationData: CoinPaginationData): Flow<EitherResult<List<CryptoCoin>>>
}
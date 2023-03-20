package br.com.dluche.criptocoinviewer.domain.usescases

import br.com.dluche.criptocoinviewer.common.EitherResult
import br.com.dluche.criptocoinviewer.domain.model.CoinPaginationData
import br.com.dluche.criptocoinviewer.domain.model.CryptoCoin
import br.com.dluche.criptocoinviewer.domain.repository.CryptoCoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetCoinsUseCaseImpl @Inject constructor(
    private val repository: CryptoCoinRepository
) : GetCoinsUseCase {
    override suspend fun invoke(
        forceReset: Boolean,
        paginationData: CoinPaginationData
    ): Flow<EitherResult<List<CryptoCoin>>> = flow {
        emit(repository.getCoins(forceReset = forceReset, paginationData = paginationData))
    }
}
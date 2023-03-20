package br.com.dluche.criptocoinviewer.domain.usescases

import br.com.dluche.criptocoinviewer.common.EitherResult
import br.com.dluche.criptocoinviewer.domain.model.CoinPaginationData
import br.com.dluche.criptocoinviewer.domain.model.CryptoCoin
import br.com.dluche.criptocoinviewer.domain.repository.CryptoCoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetCoinsNextPageUseCaseImpl @Inject constructor(
    private val repository: CryptoCoinRepository
) : GetCoinsNextPageUseCase {
    override suspend fun invoke(
        paginationData: CoinPaginationData
    ): Flow<EitherResult<List<CryptoCoin>>> = flow {
        emit(
            repository.getCoinsNextPage(
                paginationData
            )
        )
    }
}
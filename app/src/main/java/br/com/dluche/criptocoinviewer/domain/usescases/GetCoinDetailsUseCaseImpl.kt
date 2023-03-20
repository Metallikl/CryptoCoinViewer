package br.com.dluche.criptocoinviewer.domain.usescases

import br.com.dluche.criptocoinviewer.common.EitherResult
import br.com.dluche.criptocoinviewer.domain.model.CryptoCoinDetails
import br.com.dluche.criptocoinviewer.domain.repository.CryptoCoinRepository
import javax.inject.Inject

class GetCoinDetailsUseCaseImpl @Inject constructor(
    private val repository: CryptoCoinRepository
) : GetCoinDetailsUseCase {
    override suspend fun invoke(coinId: String): EitherResult<CryptoCoinDetails> {
        return repository.getCoinDetails(coinId = coinId)
    }
}
package br.com.dluche.criptocoinviewer.domain.usescases

import br.com.dluche.criptocoinviewer.common.EitherResult
import br.com.dluche.criptocoinviewer.domain.model.CryptoCoinDetails

interface GetCoinDetailsUseCase {
    suspend operator fun invoke(coinId: String): EitherResult<CryptoCoinDetails>
}
package br.com.dluche.criptocoinviewer.domain.model

import br.com.dluche.criptocoinviewer.extensions.emptyString

data class CryptoCoin(
    val id: String = String().emptyString(),
    val isActive: Boolean = false,
    val isNew: Boolean = false,
    val name: String = String().emptyString(),
    val rank: Int = 0,
    val symbol: String = String().emptyString(),
    val type: CryptoCoinType = CryptoCoinType.COIN
)

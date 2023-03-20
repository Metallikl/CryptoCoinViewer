package br.com.dluche.criptocoinviewer.domain.model

data class CoinPaginationData(
    val search: String? = null,
    val offset: Int = 0,
    val limit: Int = 20
)

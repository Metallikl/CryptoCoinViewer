package br.com.dluche.criptocoinviewer.data.api

import br.com.dluche.criptocoinviewer.data.model.response.crypto_coin.CryptoCoinDto
import br.com.dluche.criptocoinviewer.data.model.response.crypto_coin_detail.CryptoDetailsDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Path

interface PaprikaCoinApi {
    @GET("v1/coins")
    suspend fun getCoins(): Response<List<CryptoCoinDto>>

    @GET("v1/coins/{coinId}")
    suspend fun getCoinDetail(@Path("coinId") coinId: String): Response<CryptoDetailsDto>
}
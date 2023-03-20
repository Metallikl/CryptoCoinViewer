package br.com.dluche.criptocoinviewer.data.model.response.crypto_coin_detail


import com.google.gson.annotations.SerializedName

data class LinksExtendedDto(
    @SerializedName("stats")
    val stats: Stats = Stats(),
    @SerializedName("type")
    val type: String = "",
    @SerializedName("url")
    val url: String = ""
)
package br.com.dluche.criptocoinviewer.data.model.response.crypto_coin_detail


import com.google.gson.annotations.SerializedName

data class Whitepaper(
    @SerializedName("link")
    val link: String = "",
    @SerializedName("thumbnail")
    val thumbnail: String = ""
)
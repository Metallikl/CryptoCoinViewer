package br.com.dluche.criptocoinviewer.data.model.response.crypto_coin_detail


import com.google.gson.annotations.SerializedName

data class TagDto(
    @SerializedName("coin_counter")
    val coinCounter: Int = 0,
    @SerializedName("ico_counter")
    val icoCounter: Int = 0,
    @SerializedName("id")
    val id: String = "",
    @SerializedName("name")
    val name: String = ""
)
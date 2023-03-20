package br.com.dluche.criptocoinviewer.data.model.response.crypto_coin_detail


import com.google.gson.annotations.SerializedName

data class CryptoDetailsDto(
    @SerializedName("description")
    val description: String = "",
    @SerializedName("development_status")
    val developmentStatus: String = "",
    @SerializedName("first_data_at")
    val firstDataAt: String = "",
    @SerializedName("hardware_wallet")
    val hardwareWallet: Boolean = false,
    @SerializedName("hash_algorithm")
    val hashAlgorithm: String = "",
    @SerializedName("id")
    val id: String = "",
    @SerializedName("is_active")
    val isActive: Boolean = false,
    @SerializedName("is_new")
    val isNew: Boolean = false,
    @SerializedName("last_data_at")
    val lastDataAt: String = "",
    @SerializedName("links")
    val links: LinksDto = LinksDto(),
    @SerializedName("links_extended")
    val linksExtended: List<LinksExtendedDto> = listOf(),
    @SerializedName("logo")
    val logo: String = "",
    @SerializedName("message")
    val message: String = "",
    @SerializedName("name")
    val name: String = "",
    @SerializedName("open_source")
    val openSource: Boolean = false,
    @SerializedName("org_structure")
    val orgStructure: String = "",
    @SerializedName("proof_type")
    val proofType: String = "",
    @SerializedName("rank")
    val rank: Int = 0,
    @SerializedName("started_at")
    val startedAt: String = "",
    @SerializedName("symbol")
    val symbol: String = "",
    @SerializedName("tags")
    val tags: List<TagDto> = listOf(),
    @SerializedName("team")
    val team: List<TeamDto> = listOf(),
    @SerializedName("type")
    val type: String = "",
    @SerializedName("whitepaper")
    val whitepaper: Whitepaper = Whitepaper()
)
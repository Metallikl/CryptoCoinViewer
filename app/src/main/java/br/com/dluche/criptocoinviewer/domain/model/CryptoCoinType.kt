package br.com.dluche.criptocoinviewer.domain.model

enum class CryptoCoinType(val id: String) {
    COIN("coin"),
    TOKEN("token"),
    UNDEFINED("undefined");

    companion object {
        fun parse(value: String): CryptoCoinType {
            return values()
                .find {
                    it.id == value
                }?: UNDEFINED
        }
    }


}


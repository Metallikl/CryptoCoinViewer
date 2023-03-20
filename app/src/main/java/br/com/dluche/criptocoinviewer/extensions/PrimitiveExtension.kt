package br.com.dluche.criptocoinviewer.extensions

fun Boolean.toInt(): Int {
    return when (this) {
        true -> 1
        false -> 0
    }
}
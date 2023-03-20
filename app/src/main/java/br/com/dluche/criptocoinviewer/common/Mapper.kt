package br.com.dluche.criptocoinviewer.common

interface Mapper<in F,out T> {
    fun mapTo(from: F): T
}
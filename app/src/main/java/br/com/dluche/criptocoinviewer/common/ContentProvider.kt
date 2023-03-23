package br.com.dluche.criptocoinviewer.common

import androidx.annotation.StringRes

interface ContentProvider {
    fun getString(@StringRes res:Int): String
}
package br.com.dluche.criptocoinviewer.common

import android.content.Context
import javax.inject.Inject

class ContentProviderImpl @Inject constructor(
    private val context: Context
) : ContentProvider {
    override fun getString(res: Int): String {
        return context.getString(res)
    }
}
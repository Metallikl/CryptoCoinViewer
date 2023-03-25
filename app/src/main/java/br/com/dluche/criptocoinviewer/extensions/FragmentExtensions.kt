package br.com.dluche.criptocoinviewer.extensions

import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import br.com.dluche.criptocoinviewer.R
import com.google.android.material.snackbar.Snackbar

fun Fragment.showSnackbar(
    view: View,
    text: String,
    duration: Int = Snackbar.LENGTH_SHORT,
    actionText: String = String.emptyString(),
    actionListener: View.OnClickListener? = null
) {
    val realDuration = when (duration) {
        in -2..0 -> duration
        else -> -1
    }
    //
    Snackbar
        .make(
            view,
            text,
            realDuration
        ).apply {
            if (actionListener != null) {
                this.setAction(actionText, actionListener)
            }
            this.setTextColor(ContextCompat.getColor(context, R.color.white))
        }.show()
}

fun Fragment.updateToolbarTitle(title: String) {
    requireActivity().title = title
}


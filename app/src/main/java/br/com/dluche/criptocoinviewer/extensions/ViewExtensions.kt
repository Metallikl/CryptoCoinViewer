package br.com.dluche.criptocoinviewer.extensions

import android.widget.ImageView
import androidx.annotation.DrawableRes
import br.com.dluche.criptocoinviewer.R
import com.bumptech.glide.Glide

fun ImageView.loadSrcWithGlide(
    load: String,
    @DrawableRes placeholder: Int = R.drawable.outline_monetization_on_24
) {
    Glide
        .with(context)
        .load(load)
        .placeholder(placeholder)
        .into(this)
}

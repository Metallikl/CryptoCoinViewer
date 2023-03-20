package br.com.dluche.criptocoinviewer.presentation.coin_list.viewholder

import android.content.Context
import android.graphics.PorterDuff
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import br.com.dluche.criptocoinviewer.R
import br.com.dluche.criptocoinviewer.databinding.CryptoCoinItemBinding
import br.com.dluche.criptocoinviewer.domain.model.CryptoCoin

class CryptoCoinVH(
    private val binding: CryptoCoinItemBinding
) : RecyclerView.ViewHolder(binding.root) {
    constructor(
        parent: ViewGroup
    ) : this(
        binding = CryptoCoinItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    fun onBind(coin: CryptoCoin) {
        with(binding) {
            tvName.text = coin.name
            tvRank.text = coin.rank.toString()
            tvSymbol.text = coin.symbol
            tvType.text = coin.type
            ivActive.apply {
                setImageDrawable(AppCompatResources.getDrawable(context,R.drawable.baseline_attach_money_24))
                setColorFilter(getActiveColor(context,coin.isActive), PorterDuff.Mode.SRC_ATOP)
            }
        }
    }

    private fun getActiveColor(context: Context, isActive: Boolean): Int {
        return if (isActive) {
            context.getColor(R.color.green_200)
        } else {
            context.getColor(R.color.red_200)
        }
    }
}
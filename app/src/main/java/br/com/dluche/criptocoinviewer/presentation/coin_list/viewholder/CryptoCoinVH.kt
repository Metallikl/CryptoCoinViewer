package br.com.dluche.criptocoinviewer.presentation.coin_list.viewholder

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import br.com.dluche.criptocoinviewer.R
import br.com.dluche.criptocoinviewer.databinding.CryptoCoinItemBinding
import br.com.dluche.criptocoinviewer.domain.model.CryptoCoin
import br.com.dluche.criptocoinviewer.domain.model.CryptoCoinType

class CryptoCoinVH(
    private val binding: CryptoCoinItemBinding,
    private val onCoinClick: (coin: CryptoCoin) -> Unit
) : RecyclerView.ViewHolder(binding.root) {
    constructor(
        parent: ViewGroup,
        onCoinClick: (coin: CryptoCoin) -> Unit
    ) : this(
        binding = CryptoCoinItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ),
        onCoinClick = onCoinClick
    )

    fun onBind(coin: CryptoCoin) {
        with(binding) {
            tvName.text = coin.name
            tvRank.text = coin.rank.toString()
            tvSymbol.text = coin.symbol
            ivType.apply {
                setImageDrawable(
                    getTypeIcon(context,coin.type)
                )
            }
            ivActive.apply {
                setImageDrawable(
                    getActiveIcon(context, coin.isActive)
                )
            }
            root.setOnClickListener {
                onCoinClick(coin)
            }
        }
    }

    private fun getTypeIcon(context: Context, type: CryptoCoinType): Drawable? {
        val rIcon = when (type) {
            CryptoCoinType.COIN -> R.drawable.outline_monetization_on_24
            CryptoCoinType.TOKEN -> R.drawable.integrated_circuit_chip
            CryptoCoinType.UNDEFINED -> R.drawable.integrated_circuit_chip
        }
        //
        return ContextCompat.getDrawable(context, rIcon)
    }

    private fun getActiveIcon(context: Context, isActive: Boolean): Drawable? {
        return if (isActive) {
            ContextCompat.getDrawable(context, R.drawable.twotone_circle_24_green)
        } else {
            ContextCompat.getDrawable(context, R.drawable.twotone_circle_24_red)
        }
    }

}
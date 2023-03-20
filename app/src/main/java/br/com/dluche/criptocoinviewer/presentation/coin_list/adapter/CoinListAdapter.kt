package br.com.dluche.criptocoinviewer.presentation.coin_list.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import br.com.dluche.criptocoinviewer.domain.model.CryptoCoin
import br.com.dluche.criptocoinviewer.presentation.coin_list.viewholder.CryptoCoinVH

class CoinListAdapter : ListAdapter<CryptoCoin, CryptoCoinVH>(diff) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CryptoCoinVH {
        return CryptoCoinVH(parent)
    }

    override fun onBindViewHolder(holder: CryptoCoinVH, position: Int) {
        holder.onBind(getItem(position))
    }

    companion object {
        private val diff = object : DiffUtil.ItemCallback<CryptoCoin>() {
            override fun areItemsTheSame(oldItem: CryptoCoin, newItem: CryptoCoin): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: CryptoCoin, newItem: CryptoCoin): Boolean {
                return oldItem == newItem
            }
        }
    }
}
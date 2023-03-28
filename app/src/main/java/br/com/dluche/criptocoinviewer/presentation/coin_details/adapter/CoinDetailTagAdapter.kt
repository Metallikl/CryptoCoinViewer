package br.com.dluche.criptocoinviewer.presentation.coin_details.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.dluche.criptocoinviewer.domain.model.Tag
import br.com.dluche.criptocoinviewer.presentation.coin_details.viewholder.TagItemVH

class CoinDetailTagAdapter() : RecyclerView.Adapter<TagItemVH>() {

    private val tags = mutableListOf<Tag>()

    fun submitList(tags: List<Tag>){
        this.tags.clear()
        this.tags.addAll(tags)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TagItemVH {
        return TagItemVH(parent)
    }

    override fun getItemCount() = tags.size

    override fun onBindViewHolder(holder: TagItemVH, position: Int) {
        holder.onBind(tags[position])
    }
}
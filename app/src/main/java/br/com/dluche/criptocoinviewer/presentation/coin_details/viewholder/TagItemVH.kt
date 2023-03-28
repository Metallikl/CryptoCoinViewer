package br.com.dluche.criptocoinviewer.presentation.coin_details.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.dluche.criptocoinviewer.databinding.TagItemBinding
import br.com.dluche.criptocoinviewer.domain.model.Tag

class TagItemVH(
    private val binding: TagItemBinding
) : RecyclerView.ViewHolder(binding.root) {
    constructor(
        parent: ViewGroup
    ) : this(
        TagItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    fun onBind(tag: Tag){
        binding.tvTag.text = tag.name
    }

}
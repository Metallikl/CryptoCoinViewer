package br.com.dluche.criptocoinviewer.presentation.coin_details.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.dluche.criptocoinviewer.databinding.TagItemBinding
import br.com.dluche.criptocoinviewer.databinding.TeamItemBinding
import br.com.dluche.criptocoinviewer.domain.model.Tag
import br.com.dluche.criptocoinviewer.domain.model.Team

class TeamItemVH(
    private val binding: TeamItemBinding
) : RecyclerView.ViewHolder(binding.root) {
    constructor(
        parent: ViewGroup
    ) : this(
        TeamItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    fun onBind(team: Team) {
        with(binding) {
            tvName.text = team.name
            tvPosition.text = team.position
        }
    }
}
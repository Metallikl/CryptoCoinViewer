package br.com.dluche.criptocoinviewer.presentation.coin_details.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.dluche.criptocoinviewer.domain.model.Team
import br.com.dluche.criptocoinviewer.presentation.coin_details.viewholder.TeamItemVH

class CoinDetailTeamAdapter() : RecyclerView.Adapter<TeamItemVH>() {
    private var teams = mutableListOf<Team>()

    fun submitList(teams: List<Team>){
        this.teams.clear()
        this.teams.addAll(teams)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamItemVH {
        return TeamItemVH(parent)
    }

    override fun getItemCount() = teams.size

    override fun onBindViewHolder(holder: TeamItemVH, position: Int) {
        holder.onBind(teams[position])
    }
}
package com.example.nba_app_project

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class TeamAdapter: RecyclerView.Adapter<TeamAdapter.TeamViewHolder>() {

     var teamList = emptyList<TeamsItem>()
    fun setData(teamList: List<TeamsItem>) {
        this.teamList = teamList
        notifyDataSetChanged()
    }

    fun getSortedData(): List<TeamsItem> {
        return teamList.sortedBy { it.full_name }
    }


    class TeamViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var teamName: TextView = itemView.findViewById(R.id.TeamName)
        var teamWins: TextView = itemView.findViewById(R.id.wins)
        var teamLosses: TextView = itemView.findViewById(R.id.losses)
        var teamcard : CardView = itemView.findViewById(R.id.team_card)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamViewHolder {
        val view : View = LayoutInflater.from(parent.context).inflate(R.layout.team_card,parent,false)
        return TeamViewHolder(view)
    }

    override fun getItemCount(): Int {
        return teamList.size
    }

    override fun onBindViewHolder(holder: TeamViewHolder, position: Int) {
        holder.teamName.text = teamList[position].full_name
        holder.teamWins.text = "W: "+ teamList[position].wins.toString()
        holder.teamLosses.text = "L: "+teamList[position].losses.toString()

        holder.teamcard.setOnClickListener{
            val intent = Intent(holder.teamcard.context, TeamPageActivity::class.java)
            intent.putExtra("team", teamList[position].full_name)
            intent.putExtra("wins", teamList[position].wins.toString())
            intent.putExtra("losses", teamList[position].losses.toString())
            holder.teamcard.context.startActivity(intent)
        }


    }
}
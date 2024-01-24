package com.example.nba_app_project.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.nba_app_project.activities.PlayerListActivity
import com.example.nba_app_project.R
import com.example.nba_app_project.dataClasses.TeamsItem

class TeamAdapter: RecyclerView.Adapter<TeamAdapter.TeamViewHolder>() {

     private var teamList = mutableListOf<TeamsItem>()
    fun setData(teamList: MutableList<TeamsItem>) {
        this.teamList = teamList
        notifyDataSetChanged()
    }

    class TeamViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var teamName: TextView = itemView.findViewById(R.id.teamName)
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
        "W: ${teamList[position].wins}".also { holder.teamWins.text = it }
        "L: ${teamList[position].losses}".also { holder.teamLosses.text = it }


        holder.teamcard.setOnClickListener{
            val intent = Intent(holder.teamcard.context, PlayerListActivity::class.java)
            intent.putExtra("team", teamList[position].full_name)
            intent.putExtra("wins", teamList[position].wins.toString())
            intent.putExtra("losses", teamList[position].losses.toString())
            intent.putParcelableArrayListExtra("players", ArrayList(teamList[position].players))
            holder.teamcard.context.startActivity(intent)
        }
    }
}
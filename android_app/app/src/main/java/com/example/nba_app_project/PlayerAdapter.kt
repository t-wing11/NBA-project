package com.example.nba_app_project

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PlayerAdapter: RecyclerView.Adapter<PlayerAdapter.PlayerViewHolder>() {

     var teamList = emptyList<TeamsItem>()
     var playerList = emptyList<Player>()

    fun setData(teamList: List<TeamsItem>, position: Int){
        this.teamList = teamList
        this.playerList = teamList[position].players
        notifyDataSetChanged()
    }

    class PlayerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
       var firstName : TextView = itemView.findViewById(R.id.First_Name)
        var lastName : TextView = itemView.findViewById(R.id.Last_Name)
        var position : TextView = itemView.findViewById(R.id.position)
        var number : TextView = itemView.findViewById(R.id.number)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        val view : View = LayoutInflater.from(parent.context).inflate(R.layout.player_card,parent,false)
        return PlayerViewHolder(view)
    }

    override fun getItemCount(): Int {
        return teamList.size
    }

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        holder.firstName.text = playerList[position].first_name
        holder.lastName.text = playerList[position].last_name
        holder.position.text = playerList[position].position
        holder.number.text = playerList[position].number.toString()
    }
}
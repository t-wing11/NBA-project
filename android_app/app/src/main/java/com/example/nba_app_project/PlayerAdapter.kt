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
       var Name : TextView = itemView.findViewById(R.id.First_Name)
        var position : TextView = itemView.findViewById(R.id.position)
        var number : TextView = itemView.findViewById(R.id.number)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        val view : View = LayoutInflater.from(parent.context).inflate(R.layout.player_card,parent,false)
        return PlayerViewHolder(view)
    }

    override fun getItemCount(): Int {
        return playerList.size
    }

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        "${playerList[position].first_name} ${playerList[position].last_name}".also { holder.Name.text = it }
        holder.position.text = "Pos: "+playerList[position].position
        holder.number.text = "Num: "+playerList[position].number.toString()
    }
}
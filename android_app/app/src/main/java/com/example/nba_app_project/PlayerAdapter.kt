package com.example.nba_app_project

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PlayerAdapter: RecyclerView.Adapter<PlayerAdapter.PlayerViewHolder>() {

     private var playerList = mutableListOf<Player>()

    fun setData(playerList: MutableList<Player>){
        this.playerList = playerList
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
        Log.d("Tag","test")
        "${playerList[position].first_name} ${playerList[position].last_name}".also { holder.Name.text = it }
        "Pos: ${playerList[position].position}".also { holder.position.text = it }
        "Num: ${playerList[position].number}".also { holder.number.text = it }
    }
}
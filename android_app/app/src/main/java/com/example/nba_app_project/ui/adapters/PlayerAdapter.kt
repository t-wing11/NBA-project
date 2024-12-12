package com.example.nba_app_project.ui.adapters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.nba_app_project.data.PlayerItem
import com.example.nba_app_project.R
import com.example.nba_app_project.ui.viewHolder.ViewHolder

class PlayerAdapter: RecyclerView.Adapter<ViewHolder>() {

     private var playerList = mutableListOf<PlayerItem>()

    fun setData(playerList: MutableList<PlayerItem>){
        this.playerList = playerList
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view : View = LayoutInflater.from(parent.context).inflate(R.layout.player_card,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return playerList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindPlayerList(playerList[position])
        val bundle = Bundle()
        holder.playerCard.setOnClickListener{
            val intent = android.content.Intent(holder.playerCard.context, com.example.nba_app_project.ui.activities.PlayerCardActivity::class.java)
            bundle.putParcelable("player",playerList[position])
            intent.putExtras(bundle)
            holder.playerCard.context.startActivity(intent)
        }
    }
}
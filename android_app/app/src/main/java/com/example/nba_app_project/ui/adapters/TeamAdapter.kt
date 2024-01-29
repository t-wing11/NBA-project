package com.example.nba_app_project.ui.adapters

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.nba_app_project.ui.activities.PlayerListActivity
import com.example.nba_app_project.R
import com.example.nba_app_project.data.TeamItem
import com.example.nba_app_project.ui.viewHolder.ViewHolder

class TeamAdapter: RecyclerView.Adapter<ViewHolder>() {

     private var teamList = mutableListOf<TeamItem>()
    fun setData(teamList: MutableList<TeamItem>) {
        this.teamList = teamList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view : View = LayoutInflater.from(parent.context).inflate(R.layout.team_card,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return teamList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindTeamList(teamList[position])
        val bundle = Bundle()
        holder.teamCard.setOnClickListener{
            val intent = Intent(holder.teamCard.context, PlayerListActivity::class.java)
            bundle.putParcelable("team",teamList[position])
            intent.putExtras(bundle)
            holder.teamCard.context.startActivity(intent)
        }
    }
}
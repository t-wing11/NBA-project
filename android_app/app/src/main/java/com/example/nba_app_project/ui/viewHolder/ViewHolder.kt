package com.example.nba_app_project.ui.viewHolder

import android.view.View
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.nba_app_project.R
import com.example.nba_app_project.data.PlayerItem
import com.example.nba_app_project.data.TeamItem

class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
     lateinit var name : TextView
     lateinit var textView1 : TextView
     lateinit var textView2 : TextView
     lateinit var teamCard : CardView
     lateinit var playerCard : CardView

    fun bindTeamList(teamItem: TeamItem){
        name = itemView.findViewById(R.id.teamName)
        textView1 = itemView.findViewById(R.id.wins)
        textView2 = itemView.findViewById(R.id.losses)
        teamCard = itemView.findViewById(R.id.teamCard)

        name.text = teamItem.full_name
        "W: ${teamItem.wins}".also { textView1.text = it }
        "L: ${teamItem.losses}".also { textView2.text = it }
    }

    fun bindPlayerList(playerItem:PlayerItem){
        name = itemView.findViewById(R.id.full_name)
        textView1 = itemView.findViewById(R.id.position)
        textView2 = itemView.findViewById(R.id.number)
        playerCard = itemView.findViewById(R.id.player_card)

        "${playerItem.first_name} ${playerItem.last_name}".also { name.text = it }
        "Pos: ${playerItem.position}".also { textView1.text = it }
        "Num: ${playerItem.number}".also { textView2.text = it }
    }
}
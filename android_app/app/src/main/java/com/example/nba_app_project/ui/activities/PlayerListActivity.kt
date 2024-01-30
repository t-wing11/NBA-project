package com.example.nba_app_project.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.example.nba_app_project.ui.adapters.PlayerAdapter
import com.example.nba_app_project.R
import com.example.nba_app_project.data.TeamItem
import com.example.nba_app_project.databinding.TeamPageBinding

class PlayerListActivity : AppCompatActivity() {

    private lateinit var binding: TeamPageBinding
    private lateinit var playerAdapter: PlayerAdapter
    private lateinit var playerView : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= TeamPageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val bundle : Bundle? = intent.extras
        val teamItem : TeamItem?= bundle?.getParcelable("team")
        playerView()
        updateTeam(teamItem!!)
        }



    private fun updateTeam(teamItem: TeamItem){
        binding.teamCard.teamName.text = teamItem.full_name
        binding.teamCard.wins.text = getString(R.string.teamPageWins,teamItem.wins.toString())
        binding.teamCard.losses.text = getString(R.string.teamPageLosses,teamItem.losses.toString())
        playerAdapter.setData(teamItem.players.toMutableList())
    }

    private fun playerView(){
        playerView = binding.playerRecycle
        playerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)
        playerAdapter = PlayerAdapter()
        playerView.adapter = playerAdapter
    }
}
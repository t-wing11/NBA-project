package com.example.nba_app_project.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.example.nba_app_project.adapters.PlayerAdapter
import com.example.nba_app_project.R
import com.example.nba_app_project.databinding.TeamPageBinding

class PlayerListActivity : AppCompatActivity() {

    private lateinit var binding: TeamPageBinding
    private lateinit var playerAdapter: PlayerAdapter
    private lateinit var playerView : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= TeamPageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val intent = intent
        playerView()
        updateTeam(intent)
        }



    private fun updateTeam(intent: Intent){
        binding.teamName.text = intent.getStringExtra("team")
        binding.wins.text = getString(R.string.teamPageWins,intent.getStringExtra("wins").toString())
        binding.losses.text = getString(R.string.teamPageLosses,intent.getStringExtra("losses").toString())
        playerAdapter.setData(intent.getParcelableArrayListExtra("players")!!)
    }

    private fun playerView(){
        playerView = binding.playerRecycle
        playerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)
        playerAdapter = PlayerAdapter()
        playerView.adapter = playerAdapter
    }
}
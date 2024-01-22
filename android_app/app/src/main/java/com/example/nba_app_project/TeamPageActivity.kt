package com.example.nba_app_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.example.nba_app_project.databinding.TeamPageBinding

class TeamPageActivity : AppCompatActivity() {

    private lateinit var binding2: TeamPageBinding
    private lateinit var playeradapter: PlayerAdapter
    private lateinit var playerView : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding2= TeamPageBinding.inflate(layoutInflater)
        setContentView(binding2.root)
        val intent = intent
        playerView()
        updateTeam(intent)
        playeradapter.setData(intent.getParcelableArrayListExtra("players")!!)
        }



    private fun updateTeam(intent: Intent){
        binding2.TeamName.text = intent.getStringExtra("team")
        binding2.wins.text = getString(R.string.teamPageWins,intent.getStringExtra("wins").toString())
        binding2.losses.text = getString(R.string.teamPageLosses,intent.getStringExtra("losses").toString())
    }

    private fun playerView(){
        playerView = binding2.playerRecycle
        playerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)
        playeradapter = PlayerAdapter()
        playerView.adapter = playeradapter
    }
}
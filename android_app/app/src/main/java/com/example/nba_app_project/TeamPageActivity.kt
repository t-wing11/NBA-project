package com.example.nba_app_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.nba_app_project.databinding.TeamPageBinding

class TeamPageActivity : AppCompatActivity() {

    lateinit var binding2: TeamPageBinding
    lateinit var playeradapter: PlayerAdapter
    lateinit var playerView : RecyclerView
    lateinit var team : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding2= TeamPageBinding.inflate(layoutInflater)
        setContentView(binding2.root)

        val intent = getIntent()

        playerView()
        getPlayerData(intent)
        updateTeam(intent)
    }

    private fun getPlayerData(intent: Intent){
        team = intent.getStringExtra("team").toString()
        val viewModel = ViewModelProvider(this).get(TeamViewModel::class.java)
        val temp = "Ascending"
        viewModel.getTeams(temp)
        viewModel.observeTeamLiveData().observe(this) {
            for (i in it.indices) {
                if (it[i].full_name == team) {
                    playeradapter.setData(it, i)
                }
            }
        }
    }

    fun updateTeam(intent: Intent){
        binding2.TeamName.text = intent.getStringExtra("team")
        binding2.wins.text = getString(R.string.teamPageWins,intent.getStringExtra("wins").toString())
        binding2.losses.text = getString(R.string.teamPageLosses,intent.getStringExtra("losses").toString())
    }

    fun playerView(){
        playerView = binding2.playerRecycle
        playerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)
        playeradapter = PlayerAdapter()
        playerView.adapter = playeradapter
    }
}
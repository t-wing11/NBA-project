package com.example.nba_app_project

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.nba_app_project.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: TeamViewModel
    private lateinit var teamadapter: TeamAdapter
    private lateinit var recyclerView: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerview)
        recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)
        teamadapter= TeamAdapter()
        recyclerView.adapter = teamadapter

        viewModel = ViewModelProvider(this).get(TeamViewModel::class.java)
        viewModel.getTeams()
        //test message
        viewModel.observeTeamLiveData().observe(this, Observer {
            teamadapter.setData(it)
        })




    }


}
package com.example.nba_app_project

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.nba_app_project.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding1: ActivityMainBinding
     lateinit var viewModel: TeamViewModel
     lateinit var teamadapter: TeamAdapter
     lateinit var recyclerView: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding1= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding1.root)

        recyclerSetup(binding1)

        binding1.button.setOnClickListener{
            teamadapter.sortData()
        }
    }

    fun recyclerSetup(binding: ViewBinding){
        recyclerView = binding1.recyclerView
        recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)
        teamadapter= TeamAdapter()
        recyclerView.adapter = teamadapter
        viewModel = ViewModelProvider(this).get(TeamViewModel::class.java)
        viewModel.getTeams()
        viewModel.observeTeamLiveData().observe(this, Observer {
            teamadapter.setData(it)
        })
    }
}
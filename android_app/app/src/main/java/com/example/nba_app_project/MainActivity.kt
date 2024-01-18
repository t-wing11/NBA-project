package com.example.nba_app_project

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.nba_app_project.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

     lateinit var binding1: ActivityMainBinding
     lateinit var viewModel: TeamViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding1= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding1.root)
        viewModel = ViewModelProvider(this).get(TeamViewModel::class.java)
        viewModel.getTeams()
        viewModel.observeTeamLiveData().observe(this, Observer {
            viewModel.recyclerSetup(binding1,this).setData(it as MutableList<TeamsItem>)
        })
    }
}
package com.example.nba_app_project

import android.annotation.SuppressLint
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
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
     lateinit var recyclerView: RecyclerView
     lateinit var teamadapter: TeamAdapter
     lateinit var sortSpinner: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding1= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding1.root)
        recyclerView = binding1.recyclerView
        recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)
        teamadapter= TeamAdapter()
        recyclerView.adapter = teamadapter
        viewModel = ViewModelProvider(this).get(TeamViewModel::class.java)

        sortSpinner = findViewById(R.id.sort_options)
        ArrayAdapter.createFromResource(this, R.array.sort_options, android.R.layout.simple_spinner_item).
        also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            sortSpinner.adapter = adapter
        }
        sortSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long){
                viewModel.getTeams(parent?.getItemAtPosition(position).toString())
                viewModel.observeTeamLiveData().observe(this@MainActivity, Observer {
                    teamadapter.setData(it.toMutableList())
                })
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
                viewModel.getTeams( parent?.getItemAtPosition(0).toString())
                viewModel.observeTeamLiveData().observe(this@MainActivity, Observer {
                    teamadapter.setData(it.toMutableList())
                })
            }
        }
    }
}
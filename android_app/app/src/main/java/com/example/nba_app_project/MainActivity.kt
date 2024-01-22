package com.example.nba_app_project


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.nba_app_project.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

     private lateinit var binding1: ActivityMainBinding
     private var retrofitService = TeamsApi.getInstance()
     lateinit var viewModel: TeamViewModel
     private lateinit var recyclerView: RecyclerView
     lateinit var teamadapter: TeamAdapter
     private lateinit var sortSpinner: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding1= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding1.root)
        recyclerView = binding1.recyclerView
        recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)
        teamadapter= TeamAdapter()
        recyclerView.adapter = teamadapter
        viewModel = ViewModelProvider(this, ViewModelFactory(MainRepository(retrofitService))).get(TeamViewModel::class.java)
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
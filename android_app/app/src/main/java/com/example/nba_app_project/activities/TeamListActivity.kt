package com.example.nba_app_project.activities


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.nba_app_project.api.Repository
import com.example.nba_app_project.R
import com.example.nba_app_project.adapters.TeamAdapter
import com.example.nba_app_project.viewModel.TeamViewModel
import com.example.nba_app_project.api.RetrofitService
import com.example.nba_app_project.viewModel.ViewModelFactory
import com.example.nba_app_project.databinding.ActivityMainBinding

class TeamListActivity : AppCompatActivity() {

     private lateinit var binding: ActivityMainBinding
     private var retrofitService = RetrofitService.getInstance()
     lateinit var viewModel: TeamViewModel
     private lateinit var recyclerView: RecyclerView
     lateinit var teamAdapter: TeamAdapter
     private lateinit var sortSpinner: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        recyclerView = binding.recyclerView
        recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)
        teamAdapter= TeamAdapter()
        recyclerView.adapter = teamAdapter
        viewModel = ViewModelProvider(this, ViewModelFactory(Repository(retrofitService))).get(
            TeamViewModel::class.java)
        sortSpinner = findViewById(R.id.sort_options)
        ArrayAdapter.createFromResource(this, R.array.sort_options, android.R.layout.simple_spinner_item).
        also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            sortSpinner.adapter = adapter
        }
        sortSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long){
                viewModel.fetchTeams(parent?.getItemAtPosition(position).toString())
                viewModel.observeTeamLiveData().observe(this@TeamListActivity) {
                    teamAdapter.setData(it.toMutableList())
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
                viewModel.fetchTeams( parent?.getItemAtPosition(0).toString())
                viewModel.observeTeamLiveData().observe(this@TeamListActivity) {
                    teamAdapter.setData(it.toMutableList())
                }
            }
        }
    }
}
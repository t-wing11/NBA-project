package com.example.nba_app_project.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nba_app_project.api.Repository
import com.example.nba_app_project.dataClasses.TeamsItem

class TeamViewModel(private val repository: Repository): ViewModel() {
    private var teamData = MutableLiveData<List<TeamsItem>>()
    fun fetchTeams(str : String){
        teamData = repository.getTeams(str) as MutableLiveData<List<TeamsItem>>
    }

    fun observeTeamLiveData() : LiveData<List<TeamsItem>> {

        return teamData
    }

}
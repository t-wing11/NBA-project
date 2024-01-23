package com.example.nba_app_project

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TeamViewModel(private val repository: MainRepository): ViewModel() {
    private var teamData = MutableLiveData<List<TeamsItem>>()
    fun fetchTeams(str : String){
        teamData = repository.getTeams(str) as MutableLiveData<List<TeamsItem>>
    }

    fun observeTeamLiveData() : LiveData<List<TeamsItem>> {

        return teamData
    }

}
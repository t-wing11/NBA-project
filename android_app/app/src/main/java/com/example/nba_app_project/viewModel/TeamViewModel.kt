package com.example.nba_app_project.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import com.example.nba_app_project.api.Repository
import com.example.nba_app_project.dataClasses.TeamsItem

class TeamViewModel(private val repository: Repository): ViewModel() {
    private var teamData = MutableLiveData<List<TeamsItem>>()
    fun fetchTeams(str : String){
        teamData = repository.getTeams() as MutableLiveData<List<TeamsItem>>
        when (str){
            "Ascending" -> teamData = teamData.map { it.sortedBy { it.full_name} } as MutableLiveData<List<TeamsItem>>
            "Descending" -> teamData = teamData.map { it.sortedByDescending { it.full_name} } as MutableLiveData<List<TeamsItem>>
            "Wins" -> teamData = teamData.map { it.sortedByDescending { it.wins} } as MutableLiveData<List<TeamsItem>>
            "Losses" -> teamData = teamData.map { it.sortedByDescending { it.losses} } as MutableLiveData<List<TeamsItem>>
        }
    }

    fun observeTeamLiveData() : LiveData<List<TeamsItem>> {

        return teamData
    }

}
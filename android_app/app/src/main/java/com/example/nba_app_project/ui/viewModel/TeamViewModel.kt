package com.example.nba_app_project.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import com.example.nba_app_project.network.TeamsRepository
import com.example.nba_app_project.data.TeamItem

class TeamViewModel(private val teamsRepository: TeamsRepository) : ViewModel() {
    private var teamData = MutableLiveData<List<TeamItem>>()
    fun fetchTeams(str: String): LiveData<List<TeamItem>> {
        teamData = teamsRepository.getTeams()
        when (str) {
            "Ascending" -> teamData =
                teamData.map { it.sortedBy { it.full_name } } as MutableLiveData<List<TeamItem>>

            "Descending" -> teamData =
                teamData.map { it.sortedByDescending { it.full_name } } as MutableLiveData<List<TeamItem>>

            "Wins" -> teamData =
                teamData.map { it.sortedByDescending { it.wins } } as MutableLiveData<List<TeamItem>>

            "Losses" -> teamData =
                teamData.map { it.sortedByDescending { it.losses } } as MutableLiveData<List<TeamItem>>
        }
        return teamData
    }
}

package com.example.nba_app_project.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.nba_app_project.network.TeamsRepository

class ViewModelFactory(private val teamsRepository: TeamsRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(TeamViewModel::class.java)) {
            TeamViewModel(this.teamsRepository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}
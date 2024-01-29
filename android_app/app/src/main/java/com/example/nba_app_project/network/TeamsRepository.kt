package com.example.nba_app_project.network

import androidx.lifecycle.MutableLiveData
import com.example.nba_app_project.data.TeamItem
import retrofit2.Callback
import retrofit2.Call
import retrofit2.Response

class TeamsRepository(private val teamService: TeamService) {

    fun getTeams(): MutableLiveData<List<TeamItem>> {
        val teamData = MutableLiveData<List<TeamItem>>()

        teamService.getTeams().enqueue(object : Callback<List<TeamItem>> {
            override fun onResponse(
                call: Call<List<TeamItem>>,
                response: Response<List<TeamItem>>
            ) {
                if (response.isSuccessful) {
                    teamData.value = response.body()!!

                }
            }

            override fun onFailure(call: Call<List<TeamItem>>, t: Throwable) {
                return
            }
        })
        return teamData
    }
}

package com.example.nba_app_project.network

import com.example.nba_app_project.data.TeamItem
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


interface TeamService {
    @GET("poldz123/NBA-project/master/input.json")
    fun getTeams(): Call<List<TeamItem>>

    companion object {

        private var teamService: TeamService? = null
        fun getInstance() : TeamService {

            if (teamService == null) {
                val gson = GsonBuilder()
                    .setLenient()
                    .create()
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://raw.githubusercontent.com/")
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build()
                teamService = retrofit.create(TeamService::class.java)
            }
            return teamService!!
        }
    }
}







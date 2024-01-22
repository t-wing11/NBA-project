package com.example.nba_app_project

class MainRepository(private val retrofitService: TeamsApi) {
        fun getTeams() = retrofitService.getTeams()
}
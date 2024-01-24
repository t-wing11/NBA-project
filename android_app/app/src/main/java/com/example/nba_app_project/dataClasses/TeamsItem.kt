package com.example.nba_app_project.dataClasses

data class TeamsItem(
    val full_name: String,
    val id: Int,
    val losses: Int,
    val players: List<PlayersItem>,
    val wins: Int
)


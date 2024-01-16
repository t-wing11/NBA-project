package com.example.nba_app_project

data class TeamsItem(
    val full_name: String,
    val id: Int,
    val losses: Int,
    val players: List<Player> ,
    val wins: Int
)

data class Player(
    val first_name: String,
    val id: Int,
    val last_name: String,
    val number: Int,
    val position: String
)

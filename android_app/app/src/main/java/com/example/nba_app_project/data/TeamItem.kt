package com.example.nba_app_project.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TeamItem(
    val full_name: String,
    val id: Int,
    val losses: Int,
    val players: List<PlayerItem>,
    val wins: Int
): Parcelable


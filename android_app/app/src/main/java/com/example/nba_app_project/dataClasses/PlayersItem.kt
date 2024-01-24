package com.example.nba_app_project.dataClasses

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PlayersItem(
    val first_name: String,
    val id: Int,
    val last_name: String,
    val number: Int,
    val position: String
): Parcelable

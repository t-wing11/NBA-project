package com.example.nba_app_project

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.nba_app_project.dataClasses.PlayersItem
import com.example.nba_app_project.dataClasses.TeamsItem
import junit.framework.TestCase.assertTrue
import org.junit.Test

class DataClassTest{


    @Test
    fun teamItemTest() {
        val teamList: LiveData<List<TeamsItem>>

        val teamA = TeamsItem(
            "A", 1, 1, listOf(
                PlayersItem("Joe", 1, "smith", 1, "SG"),
                PlayersItem("Joe", 1, "smith", 1, "SG"),
            ), 1
        )
        teamList = MutableLiveData(listOf(teamA))

        assertTrue(teamList.value?.get(0)?.full_name is String)
        assertTrue(teamList.value?.get(0)?.id is Int)
        assertTrue(teamList.value?.get(0)?.losses is Int)
        assertTrue(teamList.value?.get(0)?.players is List<PlayersItem>)
        assertTrue(teamList.value?.get(0)?.wins is Int)
    }

    @Test
    fun playerItemTest(){

        val teamList: LiveData<List<TeamsItem>>

        val teamA = TeamsItem(
            "A", 1, 1, listOf(
                PlayersItem("Joe", 1, "smith", 1, "SG"),
            ), 1
        )
        teamList = MutableLiveData(listOf(teamA))

        assertTrue(teamList.value?.get(0)?.players?.get(0)?.first_name is String)
        assertTrue(teamList.value?.get(0)?.players?.get(0)?.last_name is String)
        assertTrue(teamList.value?.get(0)?.players?.get(0)?.number is Int)
        assertTrue(teamList.value?.get(0)?.players?.get(0)?.position is String)

    }


}
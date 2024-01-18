package com.example.nba_app_project
import org.junit.Test
import org.mockito.kotlin.mock

class SortingTests {

    lateinit var teamAdapter: TeamAdapter

    @Test
    fun testSorting() {
        teamAdapter = mock()
        val teamList = mutableListOf<TeamsItem>(
            TeamsItem(
                "Beam B",
                1,
                2,
                listOf(
                    Player("John", 101, "Doe", 5, "Guard"),
                    Player("Jane", 102, "Smith", 10, "Forward")
                ),
                8
            ),
            TeamsItem(
                "Aeam A",
                2,
                4,
                listOf(
                    Player("Mike", 201, "Johnson", 23, "Center"),
                    Player("Sara", 202, "Williams", 7, "Guard")
                ),
                6
            )
        )
        teamAdapter = TeamAdapter()
        teamAdapter.setData(teamList)
    }
}
package com.example.nba_app_project
import org.junit.Test
import org.mockito.kotlin.mock
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SortingTests {
    private lateinit var viewModel: TeamViewModel
    private lateinit var mockRetrofitInstance: TeamsApi


    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        viewModel = TeamViewModel()
        mockRetrofitInstance = mock()
        RetrofitInstance.retrofit = mockRetrofitInstance
    }

    @Test
    fun testSorting() {

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

        val call: Call<List<TeamsItem>> = mock()
        val callback: Callback<List<TeamsItem>> = mock()
        val response: Response<List<TeamsItem>> = Response.success(teamList)

        mockRetrofitInstance.getTeams("poldz123/NBA-project/master/input.json")
        call.enqueue(callback)
        callback.onResponse(call, response)

        viewModel.getTeams("Wins")
        viewModel.observeTeamLiveData().observeForever {
            assertEquals(teamList.sortedByDescending { it.wins }, it)
        }
        viewModel.getTeams("Losses")
        viewModel.observeTeamLiveData().observeForever {
            assertEquals(teamList.sortedByDescending { it.losses }, it)
        }

    }
}

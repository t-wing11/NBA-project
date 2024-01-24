package com.example.nba_app_project
import org.junit.Test
import org.mockito.kotlin.mock
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.nba_app_project.api.Repository
import com.example.nba_app_project.api.RetrofitService
import com.example.nba_app_project.dataClasses.PlayersItem
import com.example.nba_app_project.dataClasses.TeamsItem
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SortingTest {
    private lateinit var repository: Repository
    private lateinit var mockRetrofitInstance: RetrofitService


    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        mockRetrofitInstance = mock()
        repository = Repository(mockRetrofitInstance)
    }

    @Test
    fun testSorting() {

        val teamList = mutableListOf<TeamsItem>(
            TeamsItem(
                "A",
                1,
                2,
                listOf(
                    PlayersItem("John", 101, "Doe", 5, "Guard"),
                    PlayersItem("Jane", 102, "Smith", 10, "Forward")
                ),
                2
            ),
            TeamsItem(
                "B",
                2,
                4,
                listOf(
                    PlayersItem("Mike", 201, "Johnson", 23, "Center"),
                    PlayersItem("Sara", 202, "Williams", 7, "Guard")
                ),
                6
            ),
            TeamsItem(
                "C",
                3,
                6,
                listOf(
                    PlayersItem("Bob", 301, "Brown", 15, "Forward"),
                    PlayersItem("Mary", 302, "Jones", 3, "Center")
                ),
                4

            )
        )

        val call: Call<List<TeamsItem>> = mock()
        val callback: Callback<List<TeamsItem>> = mock()
        val response: Response<List<TeamsItem>> = Response.success(teamList)


        repository.getTeams("Descending")
        call.enqueue(callback)
        callback.onResponse(call, response)

        assertEquals("C", teamList[0].full_name)

        repository.getTeams("Wins")
        call.enqueue(callback)
        callback.onResponse(call, response)

        assertEquals("B", teamList[0].full_name)


        repository.getTeams("Losses")
        call.enqueue(callback)
        callback.onResponse(call, response)

        assertEquals("C", teamList[0].full_name)
    }
}

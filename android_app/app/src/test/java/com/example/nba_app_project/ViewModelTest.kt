package com.example.nba_app_project
import org.junit.Test
import org.mockito.kotlin.mock
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.nba_app_project.api.Repository
import com.example.nba_app_project.dataClasses.PlayersItem
import com.example.nba_app_project.dataClasses.TeamsItem
import com.example.nba_app_project.viewModel.TeamViewModel
import junit.framework.TestCase
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.mockito.Mockito.`when`

class ViewModelTest {
    private lateinit var repository: Repository
    private lateinit var viewModel: TeamViewModel


    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        repository = mock<Repository>()
        viewModel = TeamViewModel(repository)

    }

    @Test
    fun fetchTeamsSuccess(){

        val teamList : LiveData<List<TeamsItem>>

        val teamA = TeamsItem("A", 1, 1, listOf(
            PlayersItem("Joe", 1, "smith", 1, "SG"),
            PlayersItem("Joe", 1, "smith", 1, "SG"),
        ), 1)

        teamList = MutableLiveData(listOf(teamA))
        `when`(repository.getTeams()).thenReturn(teamList)
        viewModel.fetchTeams("Ascending")
        TestCase.assertNotNull(viewModel.observeTeamLiveData().value)
    }

    @Test
    fun testSorting() {
        val teamList : LiveData<List<TeamsItem>>

        val teamA = TeamsItem("A", 1, 1, listOf(
            PlayersItem("Joe", 1, "smith", 1, "SG"),
            PlayersItem("Joe", 1, "smith", 1, "SG"),
        ), 1)
        val teamB = TeamsItem("C", 2, 5,listOf(
            PlayersItem("Joe", 1, "smith", 1, "SG"),
            PlayersItem("Joe", 1, "smith", 1, "SG"),
        ), 1 )
        val teamC = TeamsItem("B", 3, 1, listOf(
            PlayersItem("Joe", 1, "smith", 1, "SG"),
            PlayersItem("Joe", 1, "smith", 1, "SG"),
        ),3 )

        teamList = MutableLiveData(listOf(teamC, teamB, teamA))

        `when`(repository.getTeams()).thenReturn(teamList)

        viewModel.fetchTeams("Ascending")

        assertEquals(viewModel.observeTeamLiveData().value?.get(1)?.full_name, "B")

        viewModel.fetchTeams("Descending")

        assertEquals(viewModel.observeTeamLiveData().value?.get(0)?.full_name, "C")

        viewModel.fetchTeams("Wins")

        assertEquals(viewModel.observeTeamLiveData().value?.get(0)?.full_name, "B")

        viewModel.fetchTeams("Losses")

        assertEquals(viewModel.observeTeamLiveData().value?.get(0)?.full_name, "C")

    }
}

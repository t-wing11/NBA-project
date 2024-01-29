package com.example.nba_app_project
import org.junit.Test
import org.mockito.kotlin.mock
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.nba_app_project.network.TeamsRepository
import com.example.nba_app_project.data.PlayerItem
import com.example.nba_app_project.data.TeamItem
import com.example.nba_app_project.ui.viewModel.TeamViewModel
import junit.framework.TestCase
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.mockito.Mockito.`when`

class TeamViewModelTest {
    private lateinit var repository: TeamsRepository
    private lateinit var viewModel: TeamViewModel


    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        repository = mock<TeamsRepository>()
        viewModel = TeamViewModel(repository)

    }

    @Test
    fun fetchTeamsSuccess(){

        val teamList : LiveData<List<TeamItem>>

        val teamA = TeamItem("A", 1, 1, listOf(
            PlayerItem("Joe", 1, "smith", 1, "SG"),
            PlayerItem("Joe", 1, "smith", 1, "SG"),
        ), 1)

        teamList = MutableLiveData(listOf(teamA))
        `when`(repository.getTeams()).thenReturn(teamList)
        assertNotNull( viewModel.fetchTeams("Ascending").value)
    }

    @Test
    fun testSorting() {
        val teamList : LiveData<List<TeamItem>>

        val teamA = TeamItem("A", 1, 1, listOf(
            PlayerItem("Joe", 1, "smith", 1, "SG"),
            PlayerItem("Joe", 1, "smith", 1, "SG"),
        ), 1)
        val teamB = TeamItem("C", 2, 5,listOf(
            PlayerItem("Joe", 1, "smith", 1, "SG"),
            PlayerItem("Joe", 1, "smith", 1, "SG"),
        ), 1 )
        val teamC = TeamItem("B", 3, 1, listOf(
            PlayerItem("Joe", 1, "smith", 1, "SG"),
            PlayerItem("Joe", 1, "smith", 1, "SG"),
        ),3 )

        teamList = MutableLiveData(listOf(teamC, teamB, teamA))

        `when`(repository.getTeams()).thenReturn(teamList)

        assert( viewModel.fetchTeams("Ascending").value?.get(0)?.full_name == "B")

        assert( viewModel.fetchTeams("Ascending").value?.get(0)?.full_name == "C")

        assert( viewModel.fetchTeams("Wins").value?.get(0)?.full_name == "B")

        assert( viewModel.fetchTeams("Losses").value?.get(0)?.full_name == "C")
    }
}

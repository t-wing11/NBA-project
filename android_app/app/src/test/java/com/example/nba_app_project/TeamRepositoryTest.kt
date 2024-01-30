package com.example.nba_app_project

import androidx.lifecycle.LiveData
import com.example.nba_app_project.network.TeamsRepository
import com.example.nba_app_project.network.TeamService
import com.example.nba_app_project.data.TeamItem
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentCaptor
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.verify
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class TeamRepositoryTest{

    private lateinit var repository: TeamsRepository
    private lateinit var retrofitService: TeamService
    private lateinit var callbackCaptor: ArgumentCaptor<Callback<List<TeamItem>>>

    @Before
    fun setUp(){
        MockitoAnnotations.openMocks(this)
        retrofitService = mock(TeamService::class.java)
        callbackCaptor = ArgumentCaptor.forClass(Callback::class.java) as ArgumentCaptor<Callback<List<TeamItem>>>
        repository = TeamsRepository(retrofitService)

    }

    @Test
    fun getTeamsDataTypeTest() {
        `when`(retrofitService.getTeams()).thenReturn(mock(Call::class.java) as Call<List<TeamItem>>)
        val response = repository.getTeams()
        assertTrue(response is LiveData<List<TeamItem>>)
    }

    @Test
    fun getTeamsSuccessTest(){
        val mockCall: Call<List<TeamItem>> = mock(Call::class.java) as Call<List<TeamItem>>
        val mockResponse: Response<List<TeamItem>> = mock(Response::class.java) as Response<List<TeamItem>>
        `when`(retrofitService.getTeams()).thenReturn(mockCall)
        repository.getTeams()
        verify(mockCall).enqueue(callbackCaptor.capture())
        callbackCaptor.value.onResponse(mockCall, mockResponse)
        assertEquals(repository.getTeams().value, mockResponse.body())
    }

    @Test
    fun getTeamsFailureTest(){
        val mockCall: Call<List<TeamItem>> = mock(Call::class.java) as Call<List<TeamItem>>
        val mockThrowable: Throwable = mock(Throwable::class.java)
        `when`(retrofitService.getTeams()).thenReturn(mockCall)
        repository.getTeams()
        verify(mockCall).enqueue(callbackCaptor.capture())
        callbackCaptor.value.onFailure(mockCall, mockThrowable)
        assertEquals(repository.getTeams().value, null)
    }

}
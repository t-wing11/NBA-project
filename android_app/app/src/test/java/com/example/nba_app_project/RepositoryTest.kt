package com.example.nba_app_project

import androidx.lifecycle.LiveData
import com.example.nba_app_project.api.Repository
import com.example.nba_app_project.api.RetrofitService
import com.example.nba_app_project.dataClasses.TeamsItem
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentCaptor
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.verify
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class RepositoryTest{

    private lateinit var repository: Repository
    private lateinit var retrofitService: RetrofitService
    private lateinit var callbackCaptor: ArgumentCaptor<Callback<List<TeamsItem>>>

    @Before
    fun setUp(){
        MockitoAnnotations.openMocks(this)
        retrofitService = Mockito.mock(RetrofitService::class.java)
        callbackCaptor = ArgumentCaptor.forClass(Callback::class.java) as ArgumentCaptor<Callback<List<TeamsItem>>>
        repository = Repository(retrofitService)

    }

    @Test
    fun getTeamsDataTypeTest() {
        `when`(retrofitService.getTeams()).thenReturn(mock(Call::class.java) as Call<List<TeamsItem>>)
        val response = repository.getTeams()
        assertTrue(response is LiveData<List<TeamsItem>>)
    }

    @Test
    fun getTeamsSuccessTest(){
        val mockCall: Call<List<TeamsItem>> = mock(Call::class.java) as Call<List<TeamsItem>>
        val mockResponse: Response<List<TeamsItem>> = mock(Response::class.java) as Response<List<TeamsItem>>
        `when`(retrofitService.getTeams()).thenReturn(mockCall)
        repository.getTeams()
        verify(mockCall).enqueue(callbackCaptor.capture())
        callbackCaptor.value.onResponse(mockCall, mockResponse)
        assertEquals(repository.getTeams().value, mockResponse.body())
    }

    @Test
    fun getTeamsFailureTest(){
        val mockCall: Call<List<TeamsItem>> = mock(Call::class.java) as Call<List<TeamsItem>>
        val mockThrowable: Throwable = mock(Throwable::class.java)
        `when`(retrofitService.getTeams()).thenReturn(mockCall)
        repository.getTeams()
        verify(mockCall).enqueue(callbackCaptor.capture())
        callbackCaptor.value.onFailure(mockCall, mockThrowable)
        assertEquals(repository.getTeams().value, null)
    }

}
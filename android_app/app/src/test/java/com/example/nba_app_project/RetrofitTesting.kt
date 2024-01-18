package com.example.nba_app_project
import androidx.lifecycle.LiveData
import com.google.gson.GsonBuilder
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertNull
import junit.framework.TestCase.assertTrue
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class APITest {

    lateinit var mockWebServer: MockWebServer
    lateinit var teamsApi: TeamsApi

    @Before
    fun setUp(){
        mockWebServer = MockWebServer()
        val gson = GsonBuilder()
            .setLenient()
            .create()
        teamsApi = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(TeamsApi::class.java)
    }
    @Test
    fun testSuccess(){
        val response = MockResponse()
            .setResponseCode(200)
            .setBody("[]")
        mockWebServer.enqueue(response)
        val call = teamsApi.getTeams("poldz123/NBA-project/master/input.json")
        val actualResponse = call.execute()
        assert(actualResponse.isSuccessful)
        assert(actualResponse.body() != null && actualResponse.body()!!.isEmpty())
    }

    @Test
    fun getFailure(){
        val response = MockResponse()
            .setResponseCode(404)
            .setBody("[]")
        mockWebServer.enqueue(response)
        val call = teamsApi.getTeams("poldz123/NBA-project/master/input.json")
        val actualResponse = call.execute()
        assertFalse(actualResponse.isSuccessful)
        assertNull(actualResponse.body())
    }



    @After
    fun tearDown(){
        mockWebServer.shutdown()
    }
}
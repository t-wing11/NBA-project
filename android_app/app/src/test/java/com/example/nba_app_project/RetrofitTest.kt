package com.example.nba_app_project
import com.example.nba_app_project.api.RetrofitService
import com.google.gson.GsonBuilder
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertNotNull
import junit.framework.TestCase.assertNull
import junit.framework.TestCase.assertTrue
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitTest {

    private lateinit var mockWebServer: MockWebServer
    private lateinit var retrofitService: RetrofitService

    @Before
    fun setUp(){
        mockWebServer = MockWebServer()
        val gson = GsonBuilder()
            .setLenient()
            .create()
        retrofitService = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(RetrofitService::class.java)
    }
    @Test
    fun testSuccess(){
        val body = "[" +
                "{" +
                "\"id\": 1," + "\"full_name\": \"Atlanta Hawks\"," + "\"wins\": 20," + "\"losses\": 47," + "\"players\": [" +
                "{" +
                "\"id\": 377," + "\"first_name\": \"Jaylen\"," + "\"last_name\": \"Adams\"," + "\"position\": \"G-F\"," + "\"number\": 10" + "}" +
                "]" + "}" + "]"
        val response = MockResponse()
            .setResponseCode(200)
            .setBody(body)
        mockWebServer.enqueue(response)
        val call = retrofitService.getTeams()
        val actualResponse = call.execute()
        assertTrue(actualResponse.isSuccessful)
        assertNotNull(actualResponse.body())
        assertTrue(actualResponse.body()!!.isNotEmpty())
    }

    @Test
    fun getFailure(){
        val response = MockResponse()
            .setResponseCode(404)
            .setBody("[]")
        mockWebServer.enqueue(response)
        val call = retrofitService.getTeams()
        val actualResponse = call.execute()
        assertFalse(actualResponse.isSuccessful)
        assertNull(actualResponse.body())
    }

    @After
    fun tearDown(){
        mockWebServer.shutdown()
    }
}
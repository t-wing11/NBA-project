package com.example.nba_app_project

import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import kotlin.reflect.KProperty


interface TeamsApi {
    @GET("poldz123/NBA-project/master/input.json")
    fun getTeams(): Call<List<TeamsItem>>

    companion object {

        var retrofitService: TeamsApi? = null
        fun getInstance() : TeamsApi {

            if (retrofitService == null) {
                val gson = GsonBuilder()
                    .setLenient()
                    .create()
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://raw.githubusercontent.com/")
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build()
                retrofitService = retrofit.create(TeamsApi::class.java)
            }
            return retrofitService!!
        }
    }
}







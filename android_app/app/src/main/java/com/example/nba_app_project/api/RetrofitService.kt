package com.example.nba_app_project.api

import com.example.nba_app_project.dataClasses.TeamsItem
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


interface RetrofitService {
    @GET("poldz123/NBA-project/master/input.json")
    fun getTeams(): Call<List<TeamsItem>>

    companion object {

        private var retrofitService: RetrofitService? = null
        fun getInstance() : RetrofitService {

            if (retrofitService == null) {
                val gson = GsonBuilder()
                    .setLenient()
                    .create()
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://raw.githubusercontent.com/")
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build()
                retrofitService = retrofit.create(RetrofitService::class.java)
            }
            return retrofitService!!
        }
    }
}







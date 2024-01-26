package com.example.nba_app_project.api

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.nba_app_project.dataClasses.TeamsItem
import retrofit2.Callback
import retrofit2.Call
import retrofit2.Response

class Repository(private val retrofitService: RetrofitService) {

        fun getTeams(): LiveData<List<TeamsItem>> {
                val teamData = MutableLiveData<List<TeamsItem>>()

                retrofitService.getTeams().enqueue(object : Callback<List<TeamsItem>> {
                        override fun onResponse(call: Call<List<TeamsItem>>, response: Response<List<TeamsItem>>) {
                                if (response.isSuccessful) {
                                        teamData.value = response.body()!!

                                }
                        }
                        override fun onFailure(call: Call<List<TeamsItem>>, t: Throwable) {
                                return
                        }
                })
                return teamData
        }
}


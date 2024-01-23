package com.example.nba_app_project

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import retrofit2.Callback
import retrofit2.Call
import retrofit2.Response

class MainRepository(private val retrofitService: TeamsApi) {

        fun getTeams(str: String): LiveData<List<TeamsItem>> {
                val teamLiveData = MutableLiveData<List<TeamsItem>>()

                retrofitService.getTeams().enqueue(object : Callback<List<TeamsItem>> {
                        override fun onResponse(call: Call<List<TeamsItem>>, response: Response<List<TeamsItem>>) {
                                if (response.isSuccessful) {
                                        when (str){
                                                "Ascending" -> {
                                                        teamLiveData.value = response.body()?.sortedBy { it.full_name }
                                                }
                                                "Descending" -> {
                                                        teamLiveData.value = response.body()?.sortedByDescending { it.full_name }
                                                }
                                                "Wins" -> {
                                                        teamLiveData.value = response.body()?.sortedByDescending { it.wins }
                                                }
                                                "Losses" -> {
                                                        teamLiveData.value = response.body()?.sortedByDescending { it.losses }
                                                }
                                        }
                                }
                        }

                        override fun onFailure(call: Call<List<TeamsItem>>, t: Throwable) {
                                Log.d("TAG", t.message.toString())
                        }
                })

                return teamLiveData
        }
}


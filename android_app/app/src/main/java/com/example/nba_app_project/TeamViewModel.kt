package com.example.nba_app_project

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TeamViewModel(private val repository: MainRepository): ViewModel() {
    private var teamLiveData = MutableLiveData<List<TeamsItem>>()

    fun getTeams(str: String) {
        repository.getTeams().enqueue(object : Callback<List<TeamsItem>> {
            override fun onResponse(call: Call<List<TeamsItem>>, response: Response<List<TeamsItem>>) {
                if (response.isSuccessful){
                    when(str){
                        "Ascending" -> {
                            teamLiveData.postValue(response.body()?.sortedBy { it.full_name })
                        }
                        "Descending" -> {
                            teamLiveData.postValue(response.body()?.sortedByDescending { it.full_name })
                        }
                        "Wins" -> {
                            teamLiveData.postValue(response.body()?.sortedByDescending { it.wins })
                        }
                        "Losses" -> {
                            teamLiveData.postValue(response.body()?.sortedByDescending { it.losses })
                        }
                    }
                }
                else{
                    return
                }
            }
            override fun onFailure(call: Call<List<TeamsItem>>, t: Throwable) {
                Log.d("TAG",t.message.toString())
            }
        })
    }
    fun observeTeamLiveData() : LiveData<List<TeamsItem>> {

        return teamLiveData
    }


}
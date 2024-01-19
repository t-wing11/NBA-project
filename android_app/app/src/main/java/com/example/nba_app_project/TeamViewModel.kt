package com.example.nba_app_project

import android.content.Context
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.example.nba_app_project.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TeamViewModel: ViewModel() {
    private var teamLiveData = MutableLiveData<List<TeamsItem>>()

    fun getTeams(str: String) {
        RetrofitInstance.retrofit.getTeams("poldz123/NBA-project/master/input.json").enqueue(object  :
            Callback<List<TeamsItem>> {

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
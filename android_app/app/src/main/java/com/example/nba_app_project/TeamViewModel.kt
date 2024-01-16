package com.example.nba_app_project

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.reflect.Constructor

class TeamViewModel: ViewModel() {
    private var teamLiveData = MutableLiveData<List<TeamsItem>>()
    fun getTeams() {
        RetrofitInstance.retrofit.getTeams("poldz123/NBA-project/master/input.json").enqueue(object  :
            Callback<List<TeamsItem>> {

            override fun onResponse(call: Call<List<TeamsItem>>, response: Response<List<TeamsItem>>) {
                if (response.isSuccessful){
                    teamLiveData.postValue(response.body())
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
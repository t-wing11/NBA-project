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
    fun getTeams(@Query("api_key") apiKey: String): Call<List<TeamsItem>>
}
    object RetrofitInstance{
        var retrofit by lazy {
            val gson = GsonBuilder()
                .setLenient()
                .create()
            Retrofit.Builder()
                .baseUrl("https://raw.githubusercontent.com/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
                .create(TeamsApi::class.java)
    }
}

private operator fun <T> Lazy<T>.setValue(
    retrofitInstance: RetrofitInstance,
    property: KProperty<*>,
    t: T
) {
return
}




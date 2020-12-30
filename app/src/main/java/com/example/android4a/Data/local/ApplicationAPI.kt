package com.example.android4a.Data.local

import android.telecom.Call
import com.example.android4a.Presentation.main.Character
import com.example.android4a.Presentation.main.RestApplicationResponse


import retrofit2.http.GET


interface RickAndMortyApi {
    @get:GET("character/")
    val applicationResponse: Call<RestApplicationResponse?>?

    @get:GET("location/")
    val location: Call<RestApplicationResponse?>?

    @get:GET("episode/")
    val episode: Call<RestApplicationResponse?>?
}
package com.example.android4a.Data.local.models

import android.content.SharedPreferences
import com.example.RickAndMorty.Constants
import com.example.RickAndMorty.Presentation.Model.RestRickAndMortyResponse
import com.example.RickAndMorty.Singletons
import com.example.android4a.Presentation.main.MainActivity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.reflect.Type


class MainController(
    private val view: MainActivity<*>,
    gson: Gson,
    sharedPreferences: SharedPreferences
) {
    private val sharedPreferences: SharedPreferences
    private val gson: Gson
    private val Character: Char? = null
    fun onStart() {
        val characterList = dataFromCache
        if (characterList != null) {
            view.ShowList(characterList)
        } else {
            makeApiCall()
        }
    }

    private fun makeApiCall() {
        val call: Call<RestRickAndMortyResponse> =
            Singletons.getRickAndMortyApi().getRickAndMortyResponse()
        call.enqueue(object : Callback<RestRickAndMortyResponse?>() {
            fun onResponse(
                call: Call<RestRickAndMortyResponse?>?,
                response: Response<RestRickAndMortyResponse?>
            ) {
                if (response.isSuccessful() && response.body() != null) {
                    val characterList: List<Char> = response.body().getResults()
                    saveList(characterList)
                    view.ShowList(characterList)
                } else {
                    view.ShowError()
                }
            }

            fun onFailure(call: Call<RestRickAndMortyResponse?>?, t: Throwable?) {
                view.ShowError()
            }
        })
    }

    private fun saveList(characterList: List<Char>) {
        val jsonString: String = gson.toJson(characterList)
        sharedPreferences
            .edit()
            .putString("jsonCharacterList", jsonString)
            .apply()
    }

    private val dataFromCache: List<Char>?
        private get() {
            val jsonCharacter = sharedPreferences.getString(Constants.KEY_CHARACTER_LIST, null)
            return if (jsonCharacter == null) {
                null
            } else {
                val listType: Type = object : TypeToken<List<Char?>?>() {}.getType()
                gson.fromJson(jsonCharacter, listType)
            }
        }

    fun onItemClick(item: Char?) {
        view.navigateToDetails(Character)
    }

    init {
        this.gson = gson
        this.sharedPreferences = sharedPreferences
    }
}
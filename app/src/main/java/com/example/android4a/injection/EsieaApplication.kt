package com.example.android4a.injection

import android.accessibilityservice.GestureDescription
import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.example.android4a.Data.local.RickAndMortyApi
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


// Koin permet de créer nos classes plus facilement avec des Singletons etc...
// Pour que ça marche, il faut étendre l'Application Android à l'objet EsieaApplication
// Il faut ajouter dans AndroidManifest l'application que l'on pointe! ici EsieaApplication


class EsieaApplication:Application(){

    override fun onCreate(){
        super.onCreate()
        // start Koin!
        startKoin {
            // Android context
            androidContext(this@EsieaApplication)
            // modules
            modules(presentationModule, domainModule, dataModule)
        }
    }
}

object Singletons {
    private var gsonInstance: Gson? = null
    private var rickAndMortyApiInstance: RickAndMortyApi? = null
    private var sharedPreferencesInstance: SharedPreferences? = null
    val gson: Gson?
        get() {
            if (gsonInstance == null) {
                gsonInstance = GsonBuilder()
                    .setLenient()
                    .create()
            }
            return gsonInstance
        }
    val rickAndMortyApi: RickAndMortyApi?
        get() {
            if (rickAndMortyApiInstance == null) {
                val retrofit: Retrofit = GestureDescription.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build()
                rickAndMortyApiInstance = retrofit.create(RickAndMortyApi::class.java)
            }
            return rickAndMortyApiInstance
        }

    fun getSharedPreferences(context: Context): SharedPreferences? {
        if (sharedPreferencesInstance == null) {
            sharedPreferencesInstance =
                context.getSharedPreferences("application_RickAndMorty", Context.MODE_PRIVATE)
        }
        return sharedPreferencesInstance
    }
}

object Constants {
    const val BASE_URL = "https://rickandmortyapi.com/api/"
    var KEY_CHARACTER_LIST = "jsonCharacterList"
}
package com.example.android4a.injection

import android.content.Context
import androidx.room.Room
import com.example.android4a.Data.local.AppDatabase
import com.example.android4a.Data.local.DatabaseDao
import com.example.android4a.Data.repository.UserRepository
import com.example.android4a.Domain.usecase.CreateUserUseCase
import com.example.android4a.Domain.usecase.GetUserUseCase
import com.example.android4a.Presentation.main.MainViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module


import android.content.SharedPreferences;

import com.example.android4a.Data.local.models.ApplicationAPI
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


val presentationModule= module{
    factory{ MainViewModel(get(), get()) } //contrairement à singleton, factory ne reste pas en mémoire
}

val domainModule = module{
    factory { CreateUserUseCase(get()) }
    factory { GetUserUseCase(get()) }
}

val dataModule = module{
    single { UserRepository(get()) }
    single{createDatabase(androidContext())}
}

fun createDatabase(context: Context): DatabaseDao {
    val appDatabase=  Room.databaseBuilder(
        context,
        AppDatabase::class.java, "database-name"
    ).build()
    return appDatabase.databaseDao()
}

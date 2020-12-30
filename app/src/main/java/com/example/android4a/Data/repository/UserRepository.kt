package com.example.android4a.Data.repository

import com.example.android4a.Data.local.DatabaseDao
import com.example.android4a.Data.local.models.UserLocal
import com.example.android4a.Data.local.models.toData
import com.example.android4a.Data.local.models.toEntity
import com.example.android4a.Domain.entity.User

class UserRepository(
    private val databaseDao: DatabaseDao
){
    //ça dépend de l'appli mais soit appel API pour màj de la base de donnée distante
    // pour créer un utilisateur soit créer une base de données locale et user local

    suspend fun createUser(user: User){
        databaseDao.insert(user.toData())
    }

    fun getUser(email: String): User?{
        val userLocal: UserLocal? = databaseDao.findByName(email)
        return userLocal?.toEntity()
    }
}
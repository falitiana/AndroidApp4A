package com.example.android4a.Domain.usecase

import com.example.android4a.Data.repository.UserRepository
import com.example.android4a.Domain.entity.User

class CreateUserUseCase(
    private val userRepository: UserRepository
) {

    suspend fun invoke(user: User){
        userRepository.createUser(user)
    }
}
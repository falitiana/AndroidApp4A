package com.example.android4a.Domain.usecase

import com.example.android4a.Data.repository.UserRepository
import com.example.android4a.Domain.entity.User

class GetUserUseCase(
    private val userRepository: UserRepository
){
    suspend fun invoke(email: String): User? {
        return userRepository.getUser(email)
    }
}

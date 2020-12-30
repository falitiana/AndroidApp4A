package com.example.android4a.Domain.usecase

import com.example.android4a.Data.repository.UserRepository
import com.example.android4a.Domain.entity.User

import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.runBlocking
import org.junit.Test

class CreateUserUseCaseTest{

    private val userRepository: UserRepository = mockk()

    private val classUnderTest= CreateUserUseCase(userRepository)

    @Test
    fun invoke(){
        runBlocking {
            //GIVEN
            val user=User("")
            coEvery{userRepository.createUser(user) }returns Unit

            //WHEN
            classUnderTest.invoke(user)

            //THEN
            coVerify(exactly =1){ userRepository.createUser(user)}
            }
        }
}
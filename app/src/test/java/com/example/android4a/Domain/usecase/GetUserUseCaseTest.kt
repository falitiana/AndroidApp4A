package com.example.android4a.Domain.usecase

import com.example.android4a.Data.repository.UserRepository
import com.example.android4a.Domain.entity.User

import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.runBlocking
import org.junit.Test

class GetUserUseCaseTest{

    private val userRepository: UserRepository = mockk()

    private val classUnderTest= GetUserUseCase(userRepository)

    @Test
    fun `invoke with invalid email`{
        runBlocking {
            //GIVEN
            val email=""
            coEvery{userRepository.getUser(email) }returns null

            //WHEN
            val result: User? =classUnderTest.invoke(email)

            //THEN
            coVerify(exactly =1){ userRepository.getUser(email)}
            assertEquals(result, null)
        }
    }

    @Test
    fun `invoke with valid email`{
        runBlocking {
            //GIVEN
            val email="user@user.com"
            val exceptedUser= User("user@user.com")
            coEvery{userRepository.getUser(email) } returns exceptedUser

            //WHEN
            val result: User? =classUnderTest.invoke(email)

            //THEN
            coVerify(exactly =1){ userRepository.getUser(email)}
            assertEquals(result, exceptedUser)
        }
}
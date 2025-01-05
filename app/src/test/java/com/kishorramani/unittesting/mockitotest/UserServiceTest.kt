package com.kishorramani.unittesting.mockitotest

import org.junit.Assert

import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class UserServiceTest {

    //we need userRepository object to test userService
    @Mock
    lateinit var userRepository: UserRepository

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)      //it Initialize all the object which are annotated with @Mock
        Mockito.`when`(userRepository.loginUser(Mockito.anyString(), Mockito.anyString())).thenReturn(LOGIN_STATUS.INVALID_PASSWORD)
    }

    @Test
    fun testUserService() {
        val sut = UserService(userRepository)
        val status = sut.loginUser("a@a.com", "123456")
        Assert.assertEquals("Invalid Password", status)
    }
}
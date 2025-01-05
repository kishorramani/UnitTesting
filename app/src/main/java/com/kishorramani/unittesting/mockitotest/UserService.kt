package com.kishorramani.unittesting.mockitotest

class UserService(private val userRepository: UserRepository) {
    fun loginUser(email: String, password: String): String {
        val status = userRepository.loginUser(email, password)
        return when (status) {
            LOGIN_STATUS.INVALID_USER -> "Invalid User"
            LOGIN_STATUS.INVALID_PASSWORD -> "Invalid Password"
            LOGIN_STATUS.UNKNOWN_ERROR -> "Unknown Error"
            LOGIN_STATUS.SUCCESS -> "Success"
        }
    }
}
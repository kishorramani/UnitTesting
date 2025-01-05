package com.kishorramani.unittesting.mockitotest

class UserRepository {
    val users = listOf<User>(
        User(1, "Kishor", "kishor@gmail.com", "abc123456"),
        User(2, "Nikunj", "nikunj@gmail.com", "pqr123456"),
        User(3, "Nihar", "nihar@gmail.com", "xyz123456")
    )

    fun loginUser(email: String, password: String): LOGIN_STATUS {
        //fetch user from DB
        val users = users.filter { user -> user.email == email }
        return if (users.size == 1) {
            if (users[0].password == password) {
                LOGIN_STATUS.SUCCESS
            } else {
                LOGIN_STATUS.INVALID_PASSWORD
            }
        } else {
            LOGIN_STATUS.INVALID_USER
        }
    }
}
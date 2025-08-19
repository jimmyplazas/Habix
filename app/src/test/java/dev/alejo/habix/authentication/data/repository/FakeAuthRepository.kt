package dev.alejo.habix.authentication.data.repository

import dev.alejo.habix.authentication.domain.repository.AuthRepository

class FakeAuthRepository : AuthRepository {

    var fakeError: Boolean = false

    override suspend fun login(
        email: String,
        password: String
    ): Result<Unit> {
        return if(fakeError) {
            Result.failure(Exception("Error"))
        } else {
            Result.success(Unit)
        }
    }

    override suspend fun signUp(
        email: String,
        password: String
    ): Result<Unit> {
        return if(fakeError) {
            Result.failure(Exception("Error"))
        } else {
            Result.success(Unit)
        }
    }

    override fun logout() {  }

}
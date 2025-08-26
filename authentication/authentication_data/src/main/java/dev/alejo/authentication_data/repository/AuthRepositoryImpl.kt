package dev.alejo.authentication_data.repository

import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import dev.alejo.authentication_domain.repository.AuthRepository
import kotlinx.coroutines.tasks.await

class AuthRepositoryImpl : AuthRepository {
    override suspend fun login(email: String, password: String): Result<Unit> {
        return try {
            Firebase.auth.signInWithEmailAndPassword(email, password).await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun signUp(email: String, password: String): Result<Unit> {
        return try {
            Firebase.auth.createUserWithEmailAndPassword(email, password).await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override fun logout() {
        Firebase.auth.signOut()
    }
}
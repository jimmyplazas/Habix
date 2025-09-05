package dev.alejo.authentication_data.session

import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import dev.alejo.core_domain.session.SessionManager
import kotlinx.coroutines.tasks.await

class SessionManagerImpl : SessionManager {
    override fun getUserId(): String? = getCurrentUser()?.uid
    override suspend fun getUserToken(): String? = getCurrentUser()?.getIdToken(false)?.await()?.token
    private fun getCurrentUser() = Firebase.auth.currentUser
}
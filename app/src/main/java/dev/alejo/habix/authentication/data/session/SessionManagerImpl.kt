package dev.alejo.habix.authentication.data.session

import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import dev.alejo.habix.core.session.SessionManager
import kotlinx.coroutines.tasks.await

class SessionManagerImpl : SessionManager {
    override fun getUserId(): String? = getCurrentUser()?.uid
    override suspend fun getUserToken(): String? = getCurrentUser()?.getIdToken(false)?.await()?.token
    private fun getCurrentUser() = Firebase.auth.currentUser
}
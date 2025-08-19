package dev.alejo.habix.core.session

interface SessionManager {
    fun getUserId(): String?
    suspend fun getUserToken(): String?
}
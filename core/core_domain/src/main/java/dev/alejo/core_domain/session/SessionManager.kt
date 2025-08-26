package dev.alejo.core_domain.session

interface SessionManager {
    fun getUserId(): String?
    suspend fun getUserToken(): String?
}
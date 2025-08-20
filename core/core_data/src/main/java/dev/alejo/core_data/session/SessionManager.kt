package dev.alejo.core_data.session

interface SessionManager {
    fun getUserId(): String?
    suspend fun getUserToken(): String?
}
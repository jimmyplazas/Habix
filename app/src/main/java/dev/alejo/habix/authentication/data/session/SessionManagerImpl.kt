package dev.alejo.habix.authentication.data.session

import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import dev.alejo.habix.core.session.SessionManager

class SessionManagerImpl : SessionManager {
    override fun getUserId(): String? = Firebase.auth.currentUser?.uid
}
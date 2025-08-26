package dev.alejo.authentication_data.matcher

import android.util.Patterns
import dev.alejo.authentication_domain.matcher.EmailMatcher

class EmailMatcherImpl : EmailMatcher {
    override fun isValid(
        email: String
    ): Boolean = Patterns.EMAIL_ADDRESS.matcher(email).matches()
}
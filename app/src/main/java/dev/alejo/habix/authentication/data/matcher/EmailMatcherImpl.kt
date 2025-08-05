package dev.alejo.habix.authentication.data.matcher

import android.util.Patterns
import dev.alejo.habix.authentication.domain.matcher.EmailMatcher

class EmailMatcherImpl : EmailMatcher {
    override fun isValid(
        email: String
    ): Boolean = Patterns.EMAIL_ADDRESS.matcher(email).matches()
}
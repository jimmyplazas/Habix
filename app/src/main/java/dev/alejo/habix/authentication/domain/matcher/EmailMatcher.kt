package dev.alejo.habix.authentication.domain.matcher

interface EmailMatcher {
    fun isValid(email: String): Boolean
}
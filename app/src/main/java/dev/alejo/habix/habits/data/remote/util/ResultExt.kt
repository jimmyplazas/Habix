package dev.alejo.habix.habits.data.remote.util

suspend inline fun <T> resultOf(
    block: suspend () -> T
): Result<T> {
    return try {
        Result.success(block())
    } catch (e: Exception) {
        println("Error en resultOf: ${e.message}")
        Result.failure(e)
    }
}

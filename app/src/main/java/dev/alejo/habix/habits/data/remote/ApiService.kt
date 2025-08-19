package dev.alejo.habix.habits.data.remote

import dev.alejo.habix.habits.data.remote.dto.HabitResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    companion object {
        const val BASE_URL = "https://habix-33b5f-default-rtdb.firebaseio.com/"
    }

    @GET("habits/{userId}.json")
    suspend fun getAllHabits(
        @Path("userId") userId: String,
        @Query("auth") token: String
    ): HabitResponse

    @PATCH("habits/{userId}.json")
    suspend fun insertHabit(
        @Path("userId") userId: String,
        @Query("auth") token: String,
        @Body habit: HabitResponse
    )

}
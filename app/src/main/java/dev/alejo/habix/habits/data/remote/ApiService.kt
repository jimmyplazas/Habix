package dev.alejo.habix.habits.data.remote

import dev.alejo.habix.habits.data.remote.dto.HabitResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH

interface ApiService {

    companion object {
        const val BASE_URL = "https://habix-33b5f-default-rtdb.firebaseio.com/"
    }

    @GET("habits.json")
    suspend fun getAllHabits(): HabitResponse

    @PATCH("habits.json")
    suspend fun insertHabit(@Body habit: HabitResponse)

}
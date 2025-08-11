package dev.alejo.habix.habits.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.alejo.habix.habits.data.alarm.AlarmHandlerImpl
import dev.alejo.habix.habits.data.local.HomeDao
import dev.alejo.habix.habits.data.local.HomeDatabase
import dev.alejo.habix.habits.data.local.typeconverter.HomeTypeConverter
import dev.alejo.habix.habits.data.remote.ApiService
import dev.alejo.habix.habits.data.repository.HabitRepositoryImpl
import dev.alejo.habix.habits.domain.alarm.AlarmHandler
import dev.alejo.habix.habits.domain.repository.HabitRepository
import dev.alejo.habix.habits.domain.usecase.detail.DetailUseCases
import dev.alejo.habix.habits.domain.usecase.detail.GetHabitByIdUseCase
import dev.alejo.habix.habits.domain.usecase.detail.InsertHabitUseCase
import dev.alejo.habix.habits.domain.usecase.home.CompleteHabitUseCase
import dev.alejo.habix.habits.domain.usecase.home.GetAllHabitsForSelectedDate
import dev.alejo.habix.habits.domain.usecase.home.HabitUseCases
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HabitModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(
            HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
        ).build()

    @Provides
    @Singleton
    fun provideApiService(): ApiService = Retrofit.Builder().baseUrl(ApiService.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build().create(ApiService::class.java)

    @Provides
    @Singleton
    fun provideHomeDao(
        context: Application
    ): HomeDao = Room
        .databaseBuilder(
            context,
            HomeDatabase::class.java,
            "habit_db"
        )
        .addTypeConverter(HomeTypeConverter())
        .build()
        .homeDao

    @Provides
    @Singleton
    fun provideHomeRepository(
        dao: HomeDao,
        api: ApiService,
        alarmHandler: AlarmHandler
    ): HabitRepository = HabitRepositoryImpl(dao, api, alarmHandler)

    @Provides
    @Singleton
    fun provideHabitUseCases(repository: HabitRepository): HabitUseCases {
        return HabitUseCases(
            getAllHabitsForSelectedDate = GetAllHabitsForSelectedDate(repository),
            completeHabitUseCase = CompleteHabitUseCase(repository)
        )
    }

    @Provides
    @Singleton
    fun provideDetailUseCases(repository: HabitRepository): DetailUseCases {
        return DetailUseCases(
            getHabitByIdUseCase = GetHabitByIdUseCase(repository),
            insertHabitUseCase = InsertHabitUseCase(repository)
        )
    }

    @Provides
    @Singleton
    fun provideAlarmHandler(
        @ApplicationContext context: Context
    ): AlarmHandler = AlarmHandlerImpl(context)

}
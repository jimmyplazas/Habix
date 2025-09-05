package dev.alejo.habits_data.di

import android.content.Context
import androidx.room.Room
import androidx.work.WorkManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.alejo.core_domain.session.SessionManager
import dev.alejo.habits_data.alarm.AlarmHandlerImpl
import dev.alejo.habits_data.local.HomeDao
import dev.alejo.habits_data.local.HomeDatabase
import dev.alejo.habits_data.local.typeconverter.HomeTypeConverter
import dev.alejo.habits_data.remote.ApiService
import dev.alejo.habits_data.repository.HabitRepositoryImpl
import dev.alejo.habits_domain.alarm.AlarmHandler
import dev.alejo.habits_domain.repository.HabitRepository
import dev.alejo.habits_domain.usecase.detail.DetailUseCases
import dev.alejo.habits_domain.usecase.detail.GetHabitByIdUseCase
import dev.alejo.habits_domain.usecase.detail.InsertHabitUseCase
import dev.alejo.habits_domain.usecase.home.CompleteHabitUseCase
import dev.alejo.habits_domain.usecase.home.GetAllHabitsForSelectedDate
import dev.alejo.habits_domain.usecase.home.HabitUseCases
import dev.alejo.habits_domain.usecase.home.HomeHabitSyncUseCase
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
    fun provideHabitUseCases(repository: HabitRepository): HabitUseCases {
        return HabitUseCases(
            getAllHabitsForSelectedDate = GetAllHabitsForSelectedDate(repository),
            completeHabitUseCase = CompleteHabitUseCase(repository),
            syncHabitsUseCase = HomeHabitSyncUseCase(repository)
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
        @ApplicationContext context: Context
    ): HomeDao = Room
        .databaseBuilder(
            context,
            HomeDatabase::class.java,
            "habit_db"
        )
        .addTypeConverter(HomeTypeConverter())
        .fallbackToDestructiveMigration(true)
        .build()
        .homeDao

    @Provides
    @Singleton
    fun provideHomeRepository(
        dao: HomeDao,
        api: ApiService,
        alarmHandler: AlarmHandler,
        workManager: WorkManager,
        sessionManager: SessionManager
    ): HabitRepository = HabitRepositoryImpl(dao, api, alarmHandler, workManager, sessionManager)

    @Provides
    @Singleton
    fun provideWorkManager(
        @ApplicationContext context: Context
    ): WorkManager = WorkManager.getInstance(context)

    @Provides
    @Singleton
    fun provideAlarmHandler(
        @ApplicationContext context: Context
    ): AlarmHandler = AlarmHandlerImpl(context)

}
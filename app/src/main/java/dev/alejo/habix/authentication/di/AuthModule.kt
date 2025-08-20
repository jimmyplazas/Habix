package dev.alejo.habix.authentication.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.alejo.core_data.session.SessionManager
import dev.alejo.habix.authentication.data.matcher.EmailMatcherImpl
import dev.alejo.habix.authentication.data.repository.AuthRepositoryImpl
import dev.alejo.habix.authentication.data.session.SessionManagerImpl
import dev.alejo.habix.authentication.domain.matcher.EmailMatcher
import dev.alejo.habix.authentication.domain.repository.AuthRepository
import dev.alejo.habix.authentication.domain.usecase.GetUserIdUseCase
import dev.alejo.habix.authentication.domain.usecase.LoginUseCases
import dev.alejo.habix.authentication.domain.usecase.LoginWithEmailUseCase
import dev.alejo.habix.authentication.domain.usecase.SignOutUseCase
import dev.alejo.habix.authentication.domain.usecase.SignUpUseCases
import dev.alejo.habix.authentication.domain.usecase.SignUpWithEmailUseCase
import dev.alejo.habix.authentication.domain.usecase.ValidateEmailUseCase
import dev.alejo.habix.authentication.domain.usecase.ValidatePasswordUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthModule {

    @Provides
    @Singleton
    fun provideSessionManager(): SessionManager = SessionManagerImpl()

    @Provides
    @Singleton
    fun provideAuthRepository(): AuthRepository = AuthRepositoryImpl()

    @Provides
    @Singleton
    fun provideEmailMatcher(): EmailMatcher = EmailMatcherImpl()

    @Provides
    @Singleton
    fun provideLoginUseCases(
        repository: AuthRepository,
        emailMatcher: EmailMatcher
    ): LoginUseCases = LoginUseCases(
        loginWithEmail = LoginWithEmailUseCase(repository),
        validateEmail = ValidateEmailUseCase(emailMatcher),
        validatePassword = ValidatePasswordUseCase()
    )

    @Provides
    @Singleton
    fun provideSignUpUseCases(
        repository: AuthRepository,
        emailMatcher: EmailMatcher
    ): SignUpUseCases = SignUpUseCases(
        signUpWithEmail = SignUpWithEmailUseCase(repository),
        validateEmail = ValidateEmailUseCase(emailMatcher),
        validatePassword = ValidatePasswordUseCase()
    )

    @Provides
    @Singleton
    fun provideGetUserIdUseCase(
        sessionManager: SessionManager
    ): GetUserIdUseCase = GetUserIdUseCase(sessionManager)

    @Provides
    @Singleton
    fun provideSignOutUseCase(
        repository: AuthRepository
    ): SignOutUseCase = SignOutUseCase(repository)

}
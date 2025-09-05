package dev.alejo.authentication_data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.alejo.authentication_data.matcher.EmailMatcherImpl
import dev.alejo.authentication_data.repository.AuthRepositoryImpl
import dev.alejo.authentication_data.session.SessionManagerImpl
import dev.alejo.authentication_domain.matcher.EmailMatcher
import dev.alejo.authentication_domain.repository.AuthRepository
import dev.alejo.authentication_domain.usecase.GetUserIdUseCase
import dev.alejo.authentication_domain.usecase.LoginUseCases
import dev.alejo.authentication_domain.usecase.LoginWithEmailUseCase
import dev.alejo.authentication_domain.usecase.SignOutUseCase
import dev.alejo.authentication_domain.usecase.SignUpUseCases
import dev.alejo.authentication_domain.usecase.SignUpWithEmailUseCase
import dev.alejo.authentication_domain.usecase.ValidateEmailUseCase
import dev.alejo.authentication_domain.usecase.ValidatePasswordUseCase
import dev.alejo.core_domain.session.SessionManager
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
package com.android.hara.di

import com.android.hara.data.datasource.HARAService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {

    @Singleton
    @Provides
    fun provideHARAService(@DataSourceModule.HARARetrofit authService: Retrofit): HARAService {
        return authService.create(HARAService::class.java)
    }

}
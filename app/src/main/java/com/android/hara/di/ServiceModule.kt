package com.android.hara.di

import com.android.hara.data.datasource.HaraService
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
    fun provideLoginService(@DataSourceModule.HaraRetrofit authService: Retrofit): HaraService {
        return authService.create(HaraService::class.java)
    }

}
package com.android.hara.di

import com.android.hara.data.datasource.HARAaloneService
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
    fun provideHARAService(@DataSourceModule.HARARetrofit haraService: Retrofit): HARAaloneService {
        return haraService.create(HARAaloneService::class.java)
    }

}
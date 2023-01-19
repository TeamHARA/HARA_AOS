package com.android.hara.di

import com.android.hara.data.datasource.HARAaloneService
import com.android.hara.data.datasource.HARAwithService
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
    fun provideHARAwithService(@DataSourceModule.HARARetrofit haraWithService: Retrofit): HARAwithService {
        return haraWithService.create(HARAwithService::class.java)
    }

    @Singleton
    @Provides
    fun provideHARAaloneService(@DataSourceModule.HARARetrofit haraAloneService: Retrofit): HARAaloneService {
        return haraAloneService.create(HARAaloneService::class.java)
    }

}
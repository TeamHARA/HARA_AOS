package com.android.hara.di

import com.android.hara.data.datasource.HaraAloneService
import com.android.hara.data.datasource.HaraWithService
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
    fun provideHARAwithService(@DataSourceModule.HARARetrofit haraWithService: Retrofit): HaraWithService {
        return haraWithService.create(HaraWithService::class.java)
    }

    @Singleton
    @Provides
    fun provideHARAaloneService(@DataSourceModule.HARARetrofit haraAloneService: Retrofit): HaraAloneService {
        return haraAloneService.create(HaraAloneService::class.java)
    }

}
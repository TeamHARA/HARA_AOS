package com.android.hara.di

import com.android.hara.data.datasource.HARAService
import com.android.hara.data.datasource.ReqresApi
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
    fun provideHARAService(@DataSourceModule.HARARetrofit haraService: Retrofit): HARAService {
        return haraService.create(HARAService::class.java)
    }

    //TODO 삭제예정
    @Singleton
    @Provides
    fun provideReqresService(@DataSourceModule.ReQresRetrofit reqresService: Retrofit): ReqresApi {
        return reqresService.create(ReqresApi::class.java)
    }

}
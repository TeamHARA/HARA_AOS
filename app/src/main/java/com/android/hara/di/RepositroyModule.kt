package com.android.hara.di

import com.android.hara.data.repository.HARARepositoryImpl
import com.android.hara.domain.repository.HARARepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindToHARARepository(HARARepositoryImpl: HARARepositoryImpl): HARARepository

}
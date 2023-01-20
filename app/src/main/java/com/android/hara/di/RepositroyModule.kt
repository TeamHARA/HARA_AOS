package com.android.hara.di

import com.android.hara.data.repository.HaraAloneRepositoryImpl
import com.android.hara.domain.repository.HaraAloneRepository
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
    abstract fun bindToHARARepository(HARARepositoryImpl: HaraAloneRepositoryImpl): HaraAloneRepository

}
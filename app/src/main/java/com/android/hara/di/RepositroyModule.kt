package com.android.hara.di

import com.android.hara.data.repository.HaraAloneRepositoryImpl
import com.android.hara.data.repository.HaraWithRepositoryImpl
import com.android.hara.domain.repository.HaraAloneRepository
import com.android.hara.domain.repository.HaraWithRepository
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
    abstract fun bindToHaraAloneRepository(HaraWithRepositoryImpl: HaraAloneRepositoryImpl): HaraAloneRepository

    @Binds
    @Singleton
    abstract fun bindToHaraWithRepository(HaraWithRepositoryImpl: HaraWithRepositoryImpl): HaraWithRepository

}
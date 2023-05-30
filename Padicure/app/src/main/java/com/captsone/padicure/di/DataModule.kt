package com.captsone.padicure.di

import com.captsone.padicure.data.Repository
import com.captsone.padicure.data.RepositoryResource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {
    @Binds
    @Singleton
    abstract fun provideDataRepository(dataRepository: Repository): RepositoryResource
}
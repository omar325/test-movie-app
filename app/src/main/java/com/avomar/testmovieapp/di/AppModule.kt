package com.avomar.testmovieapp.di

import com.avomar.testmovieapp.framework.remote.RemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideRemoteDataSource(): RemoteDataSource =
        RemoteDataSource.provideInstance()
}
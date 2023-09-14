package com.akmal.bmi_akmal_mahmudov.di

import com.akmal.bmi_akmal_mahmudov.data.respository.PersonRepository
import com.akmal.bmi_akmal_mahmudov.data.respository.SaveRepository
import com.akmal.bmi_akmal_mahmudov.data.respository.impl.PersonRepositoryImpl
import com.akmal.bmi_akmal_mahmudov.data.respository.impl.SaveRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
interface RepositoryModule {

    @Binds
    @Singleton
    fun bindLocationRepository(impl: PersonRepositoryImpl): PersonRepository

    @Binds
    @Singleton
    fun saveRepository(impl: SaveRepositoryImpl): SaveRepository
}
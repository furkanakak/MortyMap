package com.furkan.mortymap.di

import com.furkan.mortymap.data.repository.Repository
import com.furkan.mortymap.domain.repository.IRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindRepository(repository: Repository): IRepository
}
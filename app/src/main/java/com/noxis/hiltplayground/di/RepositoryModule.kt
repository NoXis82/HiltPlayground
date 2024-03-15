package com.noxis.hiltplayground.di

import com.noxis.hiltplayground.repository.MainRepository
import com.noxis.hiltplayground.repository.dao.BlogDao
import com.noxis.hiltplayground.repository.dao.CacheMapper
import com.noxis.hiltplayground.repository.network.BlogRetrofit
import com.noxis.hiltplayground.repository.network.NetworkMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun provideMainRepository(
        blogDao: BlogDao,
        retrofit: BlogRetrofit,
        cacheMapper: CacheMapper,
        networkMapper: NetworkMapper

    ): MainRepository {
        return MainRepository(
            blogDao,
            retrofit,
            cacheMapper,
            networkMapper
        )
    }
}
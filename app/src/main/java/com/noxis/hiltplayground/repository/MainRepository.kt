package com.noxis.hiltplayground.repository

import com.noxis.hiltplayground.model.Blog
import com.noxis.hiltplayground.repository.dao.BlogDao
import com.noxis.hiltplayground.repository.dao.CacheMapper
import com.noxis.hiltplayground.repository.network.BlogRetrofit
import com.noxis.hiltplayground.repository.network.NetworkMapper
import com.noxis.hiltplayground.until.DataState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MainRepository(
    private val blogDao: BlogDao,
    private val blogRetrofit: BlogRetrofit,
    private val cacheMapper: CacheMapper,
    private val networkMapper: NetworkMapper
) {
    suspend fun getBlogs(): Flow<DataState<List<Blog>>> = flow {
        emit(DataState.Loading)
        delay(1000) //эмитация долгой загрузки
        try {
            val networkBlogs = blogRetrofit.getBlogs()
            val blogs = networkMapper.mapFromResponseList(networkBlogs)
            blogs.forEach { blog ->
                blogDao.insert(cacheMapper.mapToResponse(blog))
            }
            val cachedBlogs = blogDao.getAll()
            emit(DataState.Success(cacheMapper.mapFromResponseList(cachedBlogs)))
        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }
}
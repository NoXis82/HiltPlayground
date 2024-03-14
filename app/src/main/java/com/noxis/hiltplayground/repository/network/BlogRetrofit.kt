package com.noxis.hiltplayground.repository.network

import retrofit2.http.GET

interface BlogRetrofit {
    @GET("blogs")
    suspend fun getBlogs(): List<BlogNetworkResponse>
}
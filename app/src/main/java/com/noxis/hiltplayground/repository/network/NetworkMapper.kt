package com.noxis.hiltplayground.repository.network

import com.noxis.hiltplayground.model.Blog
import com.noxis.hiltplayground.until.ResponseMapper
import javax.inject.Inject

class NetworkMapper @Inject constructor() : ResponseMapper<BlogNetworkResponse, Blog> {
    override fun mapFromResponse(response: BlogNetworkResponse): Blog {
        return Blog(
            id = response.id,
            title = response.title,
            body = response.body,
            image = response.image,
            category = response.category
        )
    }

    override fun mapToResponse(domainModel: Blog): BlogNetworkResponse {
        return BlogNetworkResponse(
            id = domainModel.id,
            title = domainModel.title,
            body = domainModel.body,
            image = domainModel.image,
            category = domainModel.category
        )
    }

    fun mapFromResponseList(values: List<BlogNetworkResponse>): List<Blog> {
        return values.map { mapFromResponse(it) }
    }

}
package com.noxis.hiltplayground.repository.dao

import com.noxis.hiltplayground.model.Blog
import com.noxis.hiltplayground.until.ResponseMapper
import javax.inject.Inject

class CacheMapper @Inject constructor() : ResponseMapper<BlogEntity, Blog> {
    override fun mapFromResponse(response: BlogEntity): Blog {
        return Blog(
            id = response.id,
            title = response.title,
            body = response.body,
            image = response.image,
            category = response.category
        )
    }

    override fun mapToResponse(domainModel: Blog): BlogEntity {
        return BlogEntity(
            id = domainModel.id,
            title = domainModel.title,
            body = domainModel.body,
            image = domainModel.image,
            category = domainModel.category
        )
    }

    fun mapFromResponseList(entities: List<BlogEntity>): List<Blog> {
        return entities.map { mapFromResponse(it) }
    }
}
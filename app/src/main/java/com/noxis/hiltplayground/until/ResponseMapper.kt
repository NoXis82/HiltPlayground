package com.noxis.hiltplayground.until

interface ResponseMapper<Response, DomainModel> {
    fun mapFromResponse(response: Response): DomainModel
    fun mapToResponse(domainModel: DomainModel): Response
}
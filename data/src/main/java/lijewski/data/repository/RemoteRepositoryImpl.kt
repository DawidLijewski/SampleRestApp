package lijewski.data.repository

import io.reactivex.Single
import lijewski.data.mapper.ResultMapper
import lijewski.data.network.RemoteApi
import lijewski.domain.entity.SearchResult
import lijewski.domain.entity.SearchQuery
import lijewski.domain.repository.RemoteRepository

class RemoteRepositoryImpl(
    private val remoteApi: RemoteApi,
    private val resultMapper: ResultMapper
) : RemoteRepository {
    override fun getSearchResultsList(searchQuery: SearchQuery): Single<List<SearchResult>> {
        return remoteApi.getRemoteResponse(searchQuery).map { resultMapper.map(it) }
    }
}

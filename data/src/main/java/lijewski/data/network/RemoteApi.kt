package lijewski.data.network

import io.reactivex.Single
import lijewski.data.response.RemoteResponse
import lijewski.domain.entity.SearchQuery
import javax.inject.Inject

class RemoteApi @Inject constructor(private val remoteEndpoint: RemoteEndpoint) {

    fun getRemoteResponse(searchQuery: SearchQuery): Single<RemoteResponse> {
        return remoteEndpoint.getSearchResults(
            searchQuery.term,
            searchQuery.country,
            searchQuery.media.value)
    }
}

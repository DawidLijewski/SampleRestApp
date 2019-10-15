package lijewski.domain.repository

import io.reactivex.Single
import lijewski.domain.entity.SearchQuery
import lijewski.domain.entity.SearchResult

interface RemoteRepository {
    fun getSearchResultsList(searchQuery: SearchQuery): Single<List<SearchResult>>
}

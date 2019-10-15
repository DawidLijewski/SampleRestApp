package lijewski.domain.usecase

import io.reactivex.Observable
import lijewski.domain.entity.SearchQuery
import lijewski.domain.entity.SearchResult
import lijewski.domain.repository.RemoteRepository
import javax.inject.Inject

class SearchUseCase @Inject constructor(private val remoteRepository: RemoteRepository) {

    sealed class Result {
        object Loading : Result()
        data class Success(val searchResults: List<SearchResult>) : Result()
        data class Failure(val throwable: Throwable) : Result()
    }

    fun execute(searchQuery: SearchQuery): Observable<Result> {
        return remoteRepository.getSearchResultsList(searchQuery)
            .toObservable()
            .map { Result.Success(it) as Result }
            .onErrorReturn { Result.Failure(it) }
            .startWith(Result.Loading)
    }
}

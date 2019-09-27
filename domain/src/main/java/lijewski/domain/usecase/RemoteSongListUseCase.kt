package lijewski.domain.usecase

import io.reactivex.Observable
import lijewski.domain.entity.Song
import lijewski.domain.repository.RemoteRepository
import javax.inject.Inject

class RemoteSongListUseCase @Inject constructor(private val remoteRepository: RemoteRepository) {

    sealed class Result {
        object Loading : Result()
        data class Success(val songs: List<Song>) : Result()
        data class Failure(val throwable: Throwable) : Result()
    }

    fun getSongs(artistName: String): Observable<Result> {
        return remoteRepository.getRemoteSongList(artistName)
            .toObservable()
            .map { Result.Success(it) as Result }
            .onErrorReturn { Result.Failure(it) }
            .startWith(Result.Loading)
    }
}

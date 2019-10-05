package lijewski.data.network

import io.reactivex.Single
import lijewski.data.response.RemoteResponse
import lijewski.domain.entity.SongQuery
import javax.inject.Inject

class RemoteApi @Inject constructor(private val remoteEndpoint: RemoteEndpoint) {

    fun getSongsResponse(songQuery: SongQuery): Single<RemoteResponse> {
        return remoteEndpoint.getSearchResults(
            songQuery.term,
            songQuery.country,
            songQuery.media.value)
    }
}

package lijewski.data.network

import io.reactivex.Single
import lijewski.data.response.RemoteResponse
import javax.inject.Inject

class RemoteApi @Inject constructor(private val remoteEndpoint: RemoteEndpoint) {

    fun getSongsResponse(artistName: String): Single<RemoteResponse> {
        return remoteEndpoint.getSongsList(artistName)
    }
}

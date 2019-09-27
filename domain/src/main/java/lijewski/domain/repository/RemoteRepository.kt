package lijewski.domain.repository

import io.reactivex.Single
import lijewski.domain.entity.Song

interface RemoteRepository {
    fun getRemoteSongList(artistName: String): Single<List<Song>>
}

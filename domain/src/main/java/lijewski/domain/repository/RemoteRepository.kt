package lijewski.domain.repository

import io.reactivex.Single
import lijewski.domain.entity.Song
import lijewski.domain.entity.SongQuery

interface RemoteRepository {
    fun getSongList(songQuery: SongQuery): Single<List<Song>>
}

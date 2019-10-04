package lijewski.data.repository

import io.reactivex.Single
import lijewski.data.mapper.SongMapper
import lijewski.data.network.RemoteApi
import lijewski.domain.entity.Song
import lijewski.domain.entity.SongQuery
import lijewski.domain.repository.RemoteRepository

class RemoteRepositoryImpl(
    private val remoteApi: RemoteApi,
    private val songMapper: SongMapper
) : RemoteRepository {
    override fun getSongList(songQuery: SongQuery): Single<List<Song>> {
        return remoteApi.getSongsResponse(songQuery).map { songMapper.map(it) }
    }
}

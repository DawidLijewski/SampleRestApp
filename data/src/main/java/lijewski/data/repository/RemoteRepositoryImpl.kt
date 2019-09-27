package lijewski.data.repository

import io.reactivex.Single
import lijewski.data.network.RemoteApi
import lijewski.data.mapper.SongMapper
import lijewski.domain.entity.Song
import lijewski.domain.repository.RemoteRepository

class RemoteRepositoryImpl(
    private val remoteApi: RemoteApi,
    private val songMapper: SongMapper
) : RemoteRepository {
    override fun getRemoteSongList(artistName: String): Single<List<Song>> {
        return remoteApi.getSongsResponse(artistName).map { songMapper.map(it) }
    }
}

package lijewski.data.mapper

import lijewski.data.response.RemoteResponse
import lijewski.domain.entity.Song
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class SongMapper @Inject constructor() {

    fun map(responseSong: RemoteResponse.RemoteSong): Song {
        val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US)
        val formatter = SimpleDateFormat("yyyy", Locale.US)
        val parsedYear = formatter.format(parser.parse(responseSong.releaseDate) ?: "")

        return Song(
            artist = responseSong.artistName,
            title = responseSong.trackName,
            year = parsedYear
        )
    }

    fun map(remoteResponse: RemoteResponse): List<Song> {
        return remoteResponse.results.map { (map(it)) }
    }
}

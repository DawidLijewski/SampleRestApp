package lijewski.data.mapper

import android.annotation.SuppressLint
import android.os.Build
import lijewski.data.response.RemoteResponse
import lijewski.domain.entity.Song
import java.text.SimpleDateFormat
import java.time.LocalDate
import javax.inject.Inject

class SongMapper @Inject constructor() {

    @SuppressLint("SimpleDateFormat")
    fun map(responseSong: RemoteResponse.RemoteSong): Song {

        val parsedYear = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            LocalDate.parse(responseSong.releaseDate).year.toString()
        } else {
            val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
            val formatter = SimpleDateFormat("yyyy")
            formatter.format(parser.parse(responseSong.releaseDate))
        }

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

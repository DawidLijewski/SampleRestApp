package lijewski.data.mapper

import lijewski.data.response.RemoteResponse
import lijewski.domain.entity.SearchResult
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class ResultMapper @Inject constructor() {

    fun map(responseResult: RemoteResponse.RemoteResult): SearchResult {
        val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US)
        val formatter = SimpleDateFormat("yyyy", Locale.US)
        val parsedYear = formatter.format(parser.parse(responseResult.releaseDate) ?: "")

        return SearchResult(
            artist = responseResult.artistName,
            title = responseResult.trackName,
            year = parsedYear,
            picUrl = responseResult.artworkUrl100
        )
    }

    fun map(remoteResponse: RemoteResponse): List<SearchResult> {
        return remoteResponse.results.map { (map(it)) }
    }
}

package lijewski.data.response

import lijewski.domain.entity.Explicitness
import lijewski.domain.entity.Kind
import java.util.*

data class RemoteResponse(
    val resultCount: Int,
    val results: List<RemoteResult>
) {
    data class RemoteResult(
        val kind: Kind,
        val artistId: Int,
        val collectionId: Int,
        val trackId: Int,
        val artistName: String,
        val collectionName: String,
        val trackName: String,
        val collectionCensoredName: String,
        val trackCensoredName: String,
        val artistViewUrl: String,
        val collectionViewUrl: String,
        val trackViewUrl: String,
        val previewUrl: String?,
        val artworkUrl30: String?,
        val artworkUrl60: String?,
        val artworkUrl100: String?,
        val collectionPrice: Float,
        val trackPrice: Float,
        val releaseDate: String,
        val collectionExplicitness: Explicitness,
        val trackExplicitness: Explicitness,
        val discCount: Int,
        val trackCount: Int,
        val trackNumber: Int,
        val trackTimeMillis: Int?,
        val country: String,
        val currency: Currency,
        val primaryGenreName: String,
        val isStreamable: Boolean
    )
}

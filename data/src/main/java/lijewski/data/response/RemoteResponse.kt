package lijewski.data.response

data class RemoteResponse(
    val resultCount: Int,
    val results: List<RemoteSong>
) {
    data class RemoteSong(
        val artistId: Int,
        val collectionId: Int,
        val trackId: Int,
        val artistName: String,
        val collectionName: String,
        val trackName: String,
        val releaseDate: String,
        val artworkUrl100: String
    )
}

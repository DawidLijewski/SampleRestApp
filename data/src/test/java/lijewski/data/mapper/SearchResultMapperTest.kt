package lijewski.data.mapper

import lijewski.data.response.RemoteResponse
import lijewski.domain.entity.Explicitness
import lijewski.domain.entity.Kind
import lijewski.domain.entity.SearchResult
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test

class SearchResultMapperTest {

    private lateinit var sut: ResultMapper
    private lateinit var response: RemoteResponse.RemoteResult
    private lateinit var result: SearchResult

    @Before
    fun setUp() {
        sut = ResultMapper()
        response = RemoteResponse.RemoteResult(
            kind = Kind.ALBUM,
            artistId = 1,
            collectionId = 1,
            trackId = 1,
            artistName = "artistName",
            collectionName = "collectionName",
            trackName = "",
            collectionCensoredName = "",
            trackCensoredName = "",
            artistViewUrl = "",
            collectionViewUrl = "",
            trackViewUrl = "",
            previewUrl = "",
            artworkUrl30 = "",
            artworkUrl60 = "",
            artworkUrl100 = "",
            collectionPrice = 1.0f,
            trackPrice = 1.0f,
            releaseDate = "2005-03-01T08:00:00Z",
            collectionExplicitness = Explicitness.CLEANED,
            trackExplicitness = Explicitness.CLEANED,
            discCount = 1,
            trackCount = 1,
            trackNumber = 1,
            trackTimeMillis = 100,
            country = "pl",
            currency = "usd",
            primaryGenreName = "",
            isStreamable = false
        )

        result = sut.map(response)

    }

    @Test
    fun mapArtist() {
        assertThat(result.artist, equalTo(response.artistName))
    }

    @Test
    fun mapTitle() {
        assertThat(result.title, equalTo(response.trackName))
    }

    @Test
    fun mapYear() {
        assertThat(result.year, equalTo("2005"))

    }
}

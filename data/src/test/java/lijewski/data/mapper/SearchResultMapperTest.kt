package lijewski.data.mapper

import lijewski.data.response.RemoteResponse
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
            1, 1, 1, "artistName", "collectionName", "trackName", "2005-03-01T08:00:00Z", "url"
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

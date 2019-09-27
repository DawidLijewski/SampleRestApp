package lijewski.data.mapper

import lijewski.data.response.RemoteResponse
import lijewski.domain.entity.Song
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test

class SongMapperTest {

    private lateinit var sut: SongMapper
    private lateinit var response: RemoteResponse.RemoteSong
    private lateinit var result: Song

    @Before
    fun setUp() {
        sut = SongMapper()
        response = RemoteResponse.RemoteSong(
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

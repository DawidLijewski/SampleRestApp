package lijewski.domain.usecase

import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import io.reactivex.Single
import lijewski.domain.entity.MediaType
import lijewski.domain.entity.SearchQuery
import lijewski.domain.entity.SearchResult
import lijewski.domain.repository.RemoteRepository
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class SearchUseCaseTest {
    @Mock
    lateinit var remoteRepository: RemoteRepository

    private lateinit var sut: SearchUseCase

    private val testQuery = SearchQuery("term", "pl", MediaType.ALL)

    @Before
    fun setUp() {
        sut = SearchUseCase(remoteRepository)
    }

    @Test
    fun getResults() {
    }

    @Test
    fun `retrieve list of results`() {
        given(remoteRepository.getSearchResultsList(testQuery)).willReturn(Single.error(Throwable()))
        sut.execute(testQuery).test()

        verify(remoteRepository).getSearchResultsList(testQuery)
    }

    @Test
    fun `shows loading to start`() {
        given(remoteRepository.getSearchResultsList(testQuery)).willReturn(Single.just(mock()))

        sut.execute(testQuery).test().assertValueAt(0) { (it == SearchUseCase.Result.Loading) }
    }

    @Test
    fun `returns the success result when success retrieving the results list`() {
        val testSearchResults = listOf<SearchResult>()
        given(remoteRepository.getSearchResultsList(testQuery)).willReturn(Single.just(testSearchResults))

        sut.execute(testQuery).test().assertValueAt(1) {
            (it as SearchUseCase.Result.Success).searchResults == testSearchResults
        }
    }

    @Test
    fun `returns the failure result when error retrieving the results list`() {
        val throwable = Throwable()
        given(remoteRepository.getSearchResultsList(testQuery)).willReturn(Single.error(throwable))

        sut.execute(testQuery).test().assertValueAt(1) {
            (it as SearchUseCase.Result.Failure).throwable == throwable
        }
    }
}
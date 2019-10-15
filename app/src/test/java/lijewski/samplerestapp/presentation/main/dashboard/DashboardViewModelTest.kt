package lijewski.samplerestapp.presentation.main.dashboard

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import lijewski.domain.entity.MediaType
import lijewski.domain.entity.SearchQuery
import lijewski.domain.entity.SearchResult
import lijewski.domain.usecase.SearchUseCase
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DashboardViewModelTest {
    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    lateinit var searchUseCase: SearchUseCase
    private lateinit var sut: DashboardViewModel

    private val scheduler = Schedulers.trampoline()

    private val testQuery = SearchQuery("term", "pl", MediaType.ALL)

    @Before
    fun setUp() {
        sut = DashboardViewModel(searchUseCase, scheduler, scheduler)
    }

    @Test
    fun `viewmodel retrieved results list`() {
        given(searchUseCase.execute(testQuery)).willReturn(Observable.just(mock()))

        sut.fetchSearchResultList(testQuery)

        verify(searchUseCase).execute(testQuery)
    }

    @Test
    fun `viewmodel show success retrieving results list`() {
        val result = arrayListOf<SearchResult>(mock(), mock(), mock())
        given(searchUseCase.execute(testQuery)).willReturn(Observable.just(SearchUseCase.Result.Success(result)))

        sut.fetchSearchResultList(testQuery)

        MatcherAssert.assertThat(sut.searchResults.value!!.size, CoreMatchers.equalTo(result.size))
        MatcherAssert.assertThat(sut.eventFetchError.value, CoreMatchers.nullValue())
    }

    @Test
    fun `viewmodel show error retrieving results list`() {
        given(searchUseCase.execute(testQuery)).willReturn(Observable.just(SearchUseCase.Result.Failure(Throwable())))

        sut.fetchSearchResultList(testQuery)

        MatcherAssert.assertThat(sut.searchResults.value, CoreMatchers.nullValue())
        MatcherAssert.assertThat(sut.eventFetchError.value, CoreMatchers.notNullValue())
    }
}

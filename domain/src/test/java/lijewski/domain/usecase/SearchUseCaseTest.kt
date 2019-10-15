package lijewski.domain.usecase

import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class SearchUseCaseTest {
    @get:Rule
    var rxJavaTestHooksResetRule = RxJavaTestHooksResetRule()

    @Mock
    lateinit var poiRepository: PoiRepository

    private lateinit var sut: GetPoiUseCase

    @Before
    fun setUp() {
        sut = GetPoiUseCase(poiRepository)
    }

    @Test
    fun getResults() {
    }
}
package lijewski.samplerestapp.presentation.main.dashboard

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import lijewski.domain.entity.SearchQuery
import lijewski.domain.entity.SearchResult
import lijewski.domain.usecase.SearchUseCase
import lijewski.samplerestapp.di.module.RxModule
import lijewski.samplerestapp.presentation.internal.Event
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Named

class DashboardViewModel @Inject constructor(
    private val searchUseCase: SearchUseCase,
    @Named(RxModule.IO) private val ioScheduler: Scheduler,
    @Named(RxModule.MAIN) private val mainScheduler: Scheduler
) : ViewModel() {
    val searchResults = MutableLiveData<List<SearchResult>>()
    val isLoading = ObservableBoolean(false)
    val isEmpty = ObservableBoolean(true)
    private val disposables = CompositeDisposable()

    override fun onCleared() {
        disposables.clear()
    }

    fun fetchSearchResultList(searchQuery: SearchQuery) {
        searchUseCase.execute(searchQuery)
            .subscribeOn(ioScheduler)
            .observeOn(mainScheduler)
            .doOnSubscribe { isLoading.set(true) }
            .subscribe { handleGetSongsResult(it) }
            .addTo(disposables)
    }

    private fun handleGetSongsResult(result: SearchUseCase.Result) {
        isLoading.set(false)
        when (result) {
            is SearchUseCase.Result.Success -> {
                searchResults.value = result.searchResults
                isEmpty.set(false)
            }
            is SearchUseCase.Result.Failure -> {
                isEmpty.set(true)
                Timber.e(result.throwable)
                displayFetchError()
            }
        }
    }

    val eventGetQueryData = MutableLiveData<Event<Any>>()

    fun getQueryData() {
        eventGetQueryData.value = Event(Any())
    }

    val eventFetchError = MutableLiveData<Event<Any>>()

    private fun displayFetchError() {
        eventFetchError.value = Event(Any())
    }
}

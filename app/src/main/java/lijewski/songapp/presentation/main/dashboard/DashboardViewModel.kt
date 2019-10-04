package lijewski.songapp.presentation.main.dashboard

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import lijewski.domain.entity.Song
import lijewski.domain.entity.SongQuery
import lijewski.domain.usecase.RemoteSongListUseCase
import lijewski.songapp.presentation.internal.Event
import timber.log.Timber
import javax.inject.Inject

class DashboardViewModel @Inject constructor(
    private val remoteSongListUseCase: RemoteSongListUseCase
) : ViewModel() {
    val songs = MutableLiveData<List<Song>>()
    val isLoading = ObservableBoolean(false)
    val isEmpty = ObservableBoolean(true)
    private val disposables = CompositeDisposable()

    override fun onCleared() {
        disposables.clear()
    }

    fun fetchSongsList(songQuery: SongQuery) {
        remoteSongListUseCase.getSongs(songQuery)
            .subscribeOn(Schedulers.io())
            .doOnSubscribe { isLoading.set(true) }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { handleGetSongsResult(it) }
            .addTo(disposables)
    }

    private fun handleGetSongsResult(result: RemoteSongListUseCase.Result) {
        isLoading.set(false)
        when (result) {
            is RemoteSongListUseCase.Result.Success -> {
                songs.value = result.songs
                isEmpty.set(false)
            }
            is RemoteSongListUseCase.Result.Failure -> {
                isEmpty.set(true)
                Timber.e(result.throwable)
                displayFetchError()
            }
        }
    }

    val eventGetSearchData = MutableLiveData<Event<Any>>()

    fun getSearchData() {
        eventGetSearchData.value = Event(Any())
    }

    val eventErrorDownloading = MutableLiveData<Event<Any>>()

    private fun displayFetchError() {
        eventErrorDownloading.value = Event(Any())
    }
}

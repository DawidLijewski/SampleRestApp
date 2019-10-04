package lijewski.songapp.presentation.dialog

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import lijewski.domain.entity.MediaType
import lijewski.domain.entity.SongQuery
import lijewski.songapp.presentation.internal.Event

class SearchViewModel : ViewModel() {
    val term = MutableLiveData<String>()
    val country = MutableLiveData<String>()
    val media = MutableLiveData<MediaType>()

    val isQueryDataError = ObservableBoolean(false)

    val eventQuerySong = MutableLiveData<Event<SongQuery>>()

    fun isQueryDataCorrect(): Boolean {
        return if (term.value.isNullOrBlank()) {
            isQueryDataError.set(true)
            false
        } else {
            isQueryDataError.set(false)
            true
        }
    }

    fun querySong() {
        term.value?.let {
            val query = SongQuery(it, null, MediaType.ALL)
            eventQuerySong.value = Event(query)
            isQueryDataError.set(true)
        }
    }
}

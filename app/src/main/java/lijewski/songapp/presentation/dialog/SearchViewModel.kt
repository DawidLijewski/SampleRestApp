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

    val isQueryDataCorrect = ObservableBoolean(true)

    val eventQuerySong = MutableLiveData<Event<SongQuery>>()

    fun querySong(): Boolean {
        return if (term.value.isNullOrBlank()) {
            isQueryDataCorrect.set(false)
            false
        } else {
            val query = SongQuery(term.value!!, "", MediaType.ALL)
            eventQuerySong.value = Event(query)
            isQueryDataCorrect.set(true)
            true
        }
    }
}

package lijewski.songapp.presentation.dialog

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import lijewski.domain.entity.MediaType
import lijewski.domain.entity.SongQuery
import lijewski.songapp.presentation.internal.Event

class SearchViewModel : ViewModel() {
    var term = MutableLiveData<String>()
    val title = MutableLiveData<String>()
    val year = MutableLiveData<String>()

    val eventQuerySong = MutableLiveData<Event<SongQuery>>()

    fun querySong() {
        val query = SongQuery(term.value!!, "", MediaType.ALL)

        eventQuerySong.value = Event(query)
    }
}

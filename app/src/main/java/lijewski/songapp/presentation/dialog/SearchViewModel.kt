package lijewski.songapp.presentation.dialog

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import lijewski.domain.entity.Song
import lijewski.songapp.presentation.internal.Event

class SearchViewModel : ViewModel() {
    val song = MutableLiveData<Song>()
    val eventQuerySong = MutableLiveData<Event<Song>>()

    fun querySong() {
        song.value?.let {
            eventQuerySong.value = Event(it)
        }
    }
}

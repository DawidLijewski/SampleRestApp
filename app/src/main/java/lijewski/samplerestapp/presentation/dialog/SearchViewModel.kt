package lijewski.samplerestapp.presentation.dialog

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import lijewski.domain.entity.MediaType
import lijewski.domain.entity.SearchQuery
import lijewski.samplerestapp.presentation.internal.Event

class SearchViewModel : ViewModel() {
    val term = MutableLiveData<String>()
    val country = MutableLiveData<String>()
    val media = MutableLiveData<MediaType>()

    val isQueryDataError = ObservableBoolean(false)

    val eventQuerySong = MutableLiveData<Event<SearchQuery>>()

    fun isQueryDataCorrect(): Boolean {
        return if (term.value.isNullOrBlank()) {
            isQueryDataError.set(true)
            false
        } else {
            isQueryDataError.set(false)
            true
        }
    }

    fun queryForResults() {
        term.value?.let {
            val query = SearchQuery(it, null, MediaType.ALL)
            eventQuerySong.value = Event(query)
            isQueryDataError.set(true)
        }
    }
}

package lijewski.songapp.presentation.dialog

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.lifecycle.ViewModelProviders
import lijewski.songapp.R

class SearchDialog : AppCompatDialogFragment() {
    companion object {
        const val TAG: String = "SearchDialog"
    }

    private lateinit var searchViewModel: SearchViewModel

    private lateinit var etSearch: EditText
    private lateinit var cbRemote: CheckBox

    override fun onAttach(context: Context) {
        super.onAttach(context)

        searchViewModel = activity?.run {
            ViewModelProviders.of(this).get(SearchViewModel::class.java)
        } ?: throw Exception("Invalid Activity for SearchViewModel")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        setDialogTitle()
        return inflater.inflate(R.layout.dialog_search, container)
    }

    private fun setDialogTitle() {
        val title = getString(R.string.search_title)
        dialog?.setTitle(title)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        etSearch = view.findViewById(R.id.search_edit_text)
        cbRemote = view.findViewById(R.id.checkbox_remote)
        val btnSearch = view.findViewById<Button>(R.id.btn_search)
        btnSearch.setOnClickListener {
            val textSearch = etSearch.text.toString()
            if (textSearch.isEmpty()) {
                view.findViewById<View>(R.id.text_error).visibility = View.VISIBLE
            } else {
                //TODO: add databinding
                searchViewModel.song.value?.artist = textSearch
                searchViewModel.querySong()
                dismiss()
            }
        }
        val btnCancel = view.findViewById<Button>(R.id.btn_cancel)
        btnCancel.setOnClickListener { dismiss() }
    }
}

package lijewski.songapp.presentation.dialog

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import lijewski.songapp.R
import lijewski.songapp.databinding.DialogSearchBinding

class SearchDialog : AppCompatDialogFragment() {
    companion object {
        const val TAG: String = "SearchDialog"
    }

    private lateinit var binding: DialogSearchBinding
    private lateinit var searchViewModel: SearchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        searchViewModel = activity?.run {
            ViewModelProviders.of(this).get(SearchViewModel::class.java)
        } ?: throw Exception("Invalid Activity for SearchViewModel")
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        return dialog
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<DialogSearchBinding>(
            inflater, R.layout.dialog_search, container, false
        ).apply {
            viewModel = searchViewModel
            lifecycleOwner = this@SearchDialog
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnSearch = view.findViewById<Button>(R.id.btn_search)
        btnSearch.setOnClickListener {
            if (searchViewModel.isQueryDataCorrect()) {
                searchViewModel.querySong()
                dismiss()
            }
        }
        val btnCancel = view.findViewById<Button>(R.id.btn_cancel)
        btnCancel.setOnClickListener { dismiss() }
    }

    override fun onResume() {
        super.onResume()

        val etSearch: EditText? = view?.findViewById(R.id.search_edit_text)
        etSearch?.setSelection(etSearch.text.length)
    }
}

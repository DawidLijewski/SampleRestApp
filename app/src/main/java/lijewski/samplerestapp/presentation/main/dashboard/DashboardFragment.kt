package lijewski.samplerestapp.presentation.main.dashboard

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import dagger.android.support.AndroidSupportInjection
import lijewski.domain.entity.SearchQuery
import lijewski.domain.entity.SearchResult
import lijewski.samplerestapp.R
import lijewski.samplerestapp.databinding.FragmentDashboardBinding
import lijewski.samplerestapp.presentation.adapter.ResultsListAdapter
import lijewski.samplerestapp.presentation.dialog.SearchDialog
import lijewski.samplerestapp.presentation.dialog.SearchViewModel
import timber.log.Timber
import javax.inject.Inject

class DashboardFragment : Fragment(), ResultsListAdapter.OnItemClickedListener {
    companion object {
        const val TAG: String = "DashboardFragment"
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var adapter: ResultsListAdapter

    private lateinit var binding: FragmentDashboardBinding

    private lateinit var dashboardViewModel: DashboardViewModel

    private lateinit var searchViewModel: SearchViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        dashboardViewModel =
            ViewModelProviders.of(this, viewModelFactory).get(DashboardViewModel::class.java)

        searchViewModel = activity?.run {
            ViewModelProviders.of(this).get(SearchViewModel::class.java)
        } ?: throw Exception("Invalid Activity for SearchViewModel")

        binding = DataBindingUtil.inflate<FragmentDashboardBinding>(
            inflater, R.layout.fragment_dashboard, container, false
        ).apply {
            viewModel = dashboardViewModel
            lifecycleOwner = this@DashboardFragment
            recyclerView.adapter = adapter
        }

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        observeViewModel()
    }

    private fun observeViewModel() {
        searchViewModel.eventQuerySong.observe(viewLifecycleOwner, Observer { event ->
            event.getContentIfNotHandled()?.let {
                onSearchDataReceived(it)
            }
        })

        dashboardViewModel.eventGetQueryData.observe(viewLifecycleOwner, Observer {
            it.getContentIfNotHandled()?.let {
                openSearchDialog()
            }
        })

        dashboardViewModel.eventFetchError.observe(viewLifecycleOwner, Observer {
            it.getContentIfNotHandled()?.let {
                showErrorToast()
            }
        })

        dashboardViewModel.searchResults.observe(viewLifecycleOwner, Observer {
            onResultsListUpdate(it)
        })
    }

    override fun onItemClicked(result: SearchResult, position: Int) {
        Timber.d("Clicked item title: %s", result.title)
    }

    private fun openSearchDialog() {
        val searchDialog = SearchDialog()
        searchDialog.setTargetFragment(this, 0)
        fragmentManager?.let { searchDialog.show(it, SearchDialog.TAG) }
    }

    private fun showErrorToast() {
        val toast = Toast.makeText(context, R.string.error_fetch, Toast.LENGTH_SHORT)
        toast.show()
    }

    private fun onSearchDataReceived(data: SearchQuery) {
        dashboardViewModel.fetchSearchResultList(data)
    }

    private fun onResultsListUpdate(list: List<SearchResult>) {
        adapter.updateSongList(list)
        binding.recyclerView.smoothScrollToPosition(0)
    }
}

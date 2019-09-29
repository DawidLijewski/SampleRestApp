package lijewski.songapp.presentation.main.dashboard

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
import lijewski.songapp.R
import lijewski.songapp.databinding.FragmentDashboardBinding
import lijewski.songapp.presentation.adapter.SongListAdapter
import lijewski.songapp.presentation.dialog.SearchDialog
import lijewski.songapp.presentation.dialog.SearchDialogContract
import javax.inject.Inject

class DashboardFragment : Fragment(), SearchDialogContract {
    companion object {
        const val TAG: String = "DashboardFragment"
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var adapter: SongListAdapter

    private lateinit var binding: FragmentDashboardBinding

    private lateinit var dashboardViewModel: DashboardViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        dashboardViewModel = ViewModelProviders.of(this, viewModelFactory).get(DashboardViewModel::class.java)

        binding = DataBindingUtil.inflate<FragmentDashboardBinding>(
            inflater, R.layout.fragment_dashboard, container, false
        ).apply {
            viewModel = dashboardViewModel
            setLifecycleOwner(this@DashboardFragment)
            recyclerView.adapter = adapter
        }

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        observeViewModel()
    }

    private fun observeViewModel() {
        dashboardViewModel.eventGetSearchData.observe(this, Observer {
            it.getContentIfNotHandled()?.let {
                openSearchDialog()
            }
        })

        dashboardViewModel.eventErrorDownloading.observe(this, Observer {
            it.getContentIfNotHandled()?.let {
                showErrorToast()
            }
        })

        dashboardViewModel.songs.observe(this, Observer {
            adapter.updateSongList(it)
            binding.recyclerView.smoothScrollToPosition(0)
        })
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

    override fun onSearchDataReceived(data: String) {
        dashboardViewModel.fetchSongsList(data)
        //TODO: handle data as LiveData and ditch onSearchDataReceived
        //TODO: prepare code for handling more data, array/pojo/enum/sealed
    }
}

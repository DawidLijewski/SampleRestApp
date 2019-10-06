package lijewski.songapp.di.module

import dagger.Module
import dagger.Provides
import lijewski.songapp.presentation.adapter.ResultsListAdapter
import lijewski.songapp.presentation.main.dashboard.DashboardFragment

@Module
class DashboardModule {
    @Provides
    fun bindResultListAdapter(onItemClickedListener: ResultsListAdapter.OnItemClickedListener): ResultsListAdapter {
        return ResultsListAdapter(onItemClickedListener)
    }

    @Provides
    fun bindClickListener(dashboardFragment: DashboardFragment): ResultsListAdapter.OnItemClickedListener {
        return dashboardFragment
    }
}

package lijewski.samplerestapp.di.module

import dagger.Module
import dagger.Provides
import lijewski.samplerestapp.presentation.adapter.ResultsListAdapter
import lijewski.samplerestapp.presentation.main.dashboard.DashboardFragment

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

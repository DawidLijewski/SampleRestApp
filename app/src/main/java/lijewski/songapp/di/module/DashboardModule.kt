package lijewski.songapp.di.module

import dagger.Module
import dagger.Provides
import lijewski.songapp.presentation.adapter.SongListAdapter

@Module
class DashboardModule {
    @Provides
    fun bindSongListAdapter(): SongListAdapter {
        return SongListAdapter()
    }
}
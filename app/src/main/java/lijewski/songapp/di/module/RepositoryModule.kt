package lijewski.songapp.di.module

import dagger.Module
import dagger.Provides
import lijewski.data.network.RemoteApi
import lijewski.data.repository.RemoteRepositoryImpl
import lijewski.data.mapper.SongMapper
import lijewski.domain.repository.RemoteRepository
import javax.inject.Singleton

@Module
class RepositoryModule {
    @Provides
    @Singleton
    fun provideSongRepository(remoteApi: RemoteApi, songMapper: SongMapper): RemoteRepository {
        return RemoteRepositoryImpl(remoteApi, songMapper)
    }
}

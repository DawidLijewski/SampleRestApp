package lijewski.samplerestapp.di.module

import dagger.Module
import dagger.Provides
import lijewski.data.network.RemoteApi
import lijewski.data.repository.RemoteRepositoryImpl
import lijewski.data.mapper.ResultMapper
import lijewski.domain.repository.RemoteRepository
import javax.inject.Singleton

@Module
class RepositoryModule {
    @Provides
    @Singleton
    fun provideSongRepository(remoteApi: RemoteApi, resultMapper: ResultMapper): RemoteRepository {
        return RemoteRepositoryImpl(remoteApi, resultMapper)
    }
}

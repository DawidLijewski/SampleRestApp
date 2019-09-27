package lijewski.songapp.di.module

import android.content.Context
import dagger.Module
import dagger.Provides
import lijewski.songapp.SongApp

@Module(includes = [ViewModelModule::class])
class AppModule {
    @Provides
    fun provideContext(application: SongApp): Context {
        return application.applicationContext
    }
}

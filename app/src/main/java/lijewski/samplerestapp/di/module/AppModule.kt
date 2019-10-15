package lijewski.samplerestapp.di.module

import android.content.Context
import dagger.Module
import dagger.Provides
import lijewski.samplerestapp.SampleRestApp

@Module(includes = [ViewModelModule::class])
class AppModule {
    @Provides
    fun provideContext(application: SampleRestApp): Context {
        return application.applicationContext
    }
}

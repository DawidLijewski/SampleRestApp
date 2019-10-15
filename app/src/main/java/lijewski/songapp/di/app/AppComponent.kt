package lijewski.songapp.di.app

import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import lijewski.songapp.SongApp
import lijewski.songapp.di.module.*
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AppModule::class,
        BuilderModule::class,
        NetworkModule::class,
        RepositoryModule::class,
        RxModule::class
    ]
)
interface AppComponent : AndroidInjector<SongApp> {
    @Component.Factory
    abstract class Factory : AndroidInjector.Factory<SongApp>
}

package lijewski.songapp.di.app

import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import lijewski.songapp.SongApp
import lijewski.songapp.di.module.AppModule
import lijewski.songapp.di.module.BuilderModule
import lijewski.songapp.di.module.NetworkModule
import lijewski.songapp.di.module.RepositoryModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AppModule::class,
        BuilderModule::class,
        NetworkModule::class,
        RepositoryModule::class
    ]
)
interface AppComponent : AndroidInjector<SongApp> {
    @Component.Factory
    abstract class Factory : AndroidInjector.Factory<SongApp>
}

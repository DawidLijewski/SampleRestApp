package lijewski.songapp

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.HasAndroidInjector
import lijewski.songapp.di.app.DaggerAppComponent
import timber.log.Timber

class SongApp : DaggerApplication(), HasAndroidInjector {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.factory().create(this)
    }

    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())
    }
}

package lijewski.samplerestapp.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import lijewski.samplerestapp.presentation.main.dashboard.DashboardFragment

@Suppress("unused")
@Module
abstract class MainModule {
    @ContributesAndroidInjector(modules = [(DashboardModule::class)])
    abstract fun contributeDashboardFragment(): DashboardFragment
}

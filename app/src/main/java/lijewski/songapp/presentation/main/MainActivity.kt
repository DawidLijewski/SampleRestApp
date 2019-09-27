package lijewski.songapp.presentation.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import dagger.android.AndroidInjection
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_main.*
import lijewski.songapp.R
import lijewski.songapp.presentation.main.dashboard.DashboardFragment
import javax.inject.Inject

class MainActivity : AppCompatActivity(), HasSupportFragmentInjector {
    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)

        if (savedInstanceState == null) {
            val fragment = DashboardFragment()
            supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container, fragment, DashboardFragment.TAG).commit()
        }
    }

    override fun supportFragmentInjector() = dispatchingAndroidInjector
}

package com.github.gibbrich.dogs.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.github.gibbrich.dogs.R
import com.github.gibbrich.dogs.di.DI
import com.github.gibbrich.dogs.manager.NavigationManager
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    internal lateinit var navigationManager: NavigationManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        DI.appComponent.inject(this)
    }

    override fun onResume() {
        super.onResume()

        navigationManager.navController = findNavController(nav_host_fragment)
    }

    override fun onPause() {
        super.onPause()

        navigationManager.navController = null
    }
}
package com.github.gibbrich.dogs.manager

import android.view.View
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.transition.Fade
import com.github.gibbrich.core.manager.Navigator
import com.github.gibbrich.core.model.Breed
import com.github.gibbrich.dogdetails.DogDetailFragment
import com.github.gibbrich.dogs.R

class NavigationManager : Navigator {
    var navController: NavController? = null

    override fun switchToBreedDetailScreen(breed: Breed) {
        navController?.navigate(
            R.id.action_dogsListFragment_to_dogDetailFragment,
            DogDetailFragment.createBundle(breed)
        )
    }
}
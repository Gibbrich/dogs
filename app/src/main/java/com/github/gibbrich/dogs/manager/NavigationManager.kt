package com.github.gibbrich.dogs.manager

import androidx.navigation.NavController
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
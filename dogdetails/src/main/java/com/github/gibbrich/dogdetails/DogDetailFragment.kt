package com.github.gibbrich.dogdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.github.gibbrich.core.model.Breed
import kotlinx.android.synthetic.main.fragment_dog_detail.*
import java.lang.IllegalStateException

class DogDetailFragment: Fragment() {
    companion object {
        private const val TAG = "DogDetailFragment"
        private const val BREED_KEY = "$TAG.BREED_KEY"

        fun createBundle(breed: Breed) = Bundle().apply {
            putParcelable(BREED_KEY, breed)
        }
    }

    private val args by lazy {
        arguments?.getParcelable(BREED_KEY) as? Breed ?: throw IllegalStateException("No breed specified")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(
        R.layout.fragment_dog_detail,
        container,
        false
    )

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        fragment_dog_detail_breed_title.text = args.name
        // todo - check working offline
        Glide.with(this)
            .load(args.photoUrl)
            .into(fragment_dog_detail_breed_image)
    }
}
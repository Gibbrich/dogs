package com.github.gibbrich.dogdetails

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.ViewCompat
import com.bumptech.glide.Glide
import com.github.gibbrich.core.model.Breed
import kotlinx.android.synthetic.main.activity_dog_detail.*
import java.lang.IllegalStateException

class DogDetailActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "DogDetailActivity"
        private const val BREED_KEY = "$TAG.BREED_KEY"

        const val BREED_IMAGE = "BREED_IMAGE"

        fun getIntent(
            context: Context,
            breed: Breed
        ) = Intent(context, DogDetailActivity::class.java).apply {
            putExtra(BREED_KEY, breed)
        }
    }

    private val breed by lazy {
        intent.extras?.getParcelable(BREED_KEY) as? Breed ?: throw IllegalStateException("No breed specified")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dog_detail)

        ViewCompat.setTransitionName(fragment_dog_detail_breed_image, BREED_IMAGE)

        Glide.with(this)
            .load(breed.photoUrl)
            .into(fragment_dog_detail_breed_image)

        supportActionBar?.title = breed.name
    }
}

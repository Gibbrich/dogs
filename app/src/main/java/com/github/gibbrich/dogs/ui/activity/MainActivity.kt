package com.github.gibbrich.dogs.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.github.gibbrich.core.model.Breed
import com.github.gibbrich.dogdetails.DogDetailActivity
import com.github.gibbrich.dogs.R
import com.github.gibbrich.dogs.ui.adapter.DogsAdapter
import com.github.gibbrich.dogs.ui.viewModel.DogsViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: DogsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val vm = ViewModelProviders.of(this).get(DogsViewModel::class.java)
        vm.state.observe(this, Observer(::handleState))

        activity_main_swipe_layout.setOnRefreshListener(vm::fetchAlbums)

        activity_main_dogs_list.layoutManager = getGridLayoutManager()
        adapter = DogsAdapter(onDogClicked = this::switchToBreedDetailScreen)
        activity_main_dogs_list.adapter = adapter
    }

    private fun handleState(state: DogsViewModel.State) = when (state) {
        DogsViewModel.State.LoadError -> {
            activity_main_swipe_layout.isRefreshing = false
        }

        DogsViewModel.State.Loading -> {
            activity_main_swipe_layout.isRefreshing = true
        }

        DogsViewModel.State.Empty -> {
            activity_main_empty_label.text = getString(R.string.activity_main_no_data)
            activity_main_empty_label.visibility = View.VISIBLE
            activity_main_dogs_list.visibility = View.GONE
            activity_main_swipe_layout.isRefreshing = false
        }

        is DogsViewModel.State.Loaded -> {
            activity_main_empty_label.visibility = View.GONE
            activity_main_dogs_list.visibility = View.VISIBLE
            adapter.addDataToStart(state.albums)
            activity_main_swipe_layout.isRefreshing = false
        }
    }

    private fun getGridLayoutManager(): GridLayoutManager {
        val glm = GridLayoutManager(this, 3)
        glm.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                if (position % 3 == 2) {
                    return 3
                }
                return when (position % 4) {
                    1, 3 -> 1
                    0, 2 -> 2
                    else ->
                        //never gonna happen
                        -1
                }
            }
        }
        glm.spanSizeLookup.isSpanIndexCacheEnabled = true
        return glm
    }

    private fun switchToBreedDetailScreen(breed: Breed) {
        val intent = DogDetailActivity.getIntent(this, breed)
        startActivity(intent)
    }
}
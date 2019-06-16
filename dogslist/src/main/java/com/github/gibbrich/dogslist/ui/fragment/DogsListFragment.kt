package com.github.gibbrich.dogslist.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.gibbrich.dogslist.R
import com.github.gibbrich.dogslist.ui.adapter.DogsAdapter
import com.github.gibbrich.dogslist.ui.viewModel.DogsViewModel
import kotlinx.android.synthetic.main.fragment_dogs_list.*

class DogsListFragment : Fragment() {

    private lateinit var adapter: DogsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(
        R.layout.fragment_dogs_list,
        container,
        false
    )

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val vm = ViewModelProviders.of(this).get(DogsViewModel::class.java)
        vm.state.observe(this, Observer(::handleState))

        fragment_dogs_list_swipe_layout.setOnRefreshListener(vm::fetchAlbums)

        // todo - use grid layout manager
        fragment_dogs_list.layoutManager = LinearLayoutManager(activity)
        adapter = DogsAdapter(onDogClicked = vm::onDogClicked)
        fragment_dogs_list.adapter = adapter
    }

    private fun handleState(state: DogsViewModel.State) = when (state) {
        DogsViewModel.State.LoadError -> {
            fragment_dogs_list_empty_label.text = getString(R.string.fragment_dogs_list_error_occured)
            fragment_dogs_list_empty_label.visibility = View.VISIBLE
            fragment_dogs_list.visibility = View.GONE
            fragment_dogs_list_swipe_layout.isRefreshing = false
        }

        DogsViewModel.State.Loading -> {
            fragment_dogs_list_empty_label.visibility = View.GONE
            fragment_dogs_list.visibility = View.GONE
            fragment_dogs_list_swipe_layout.isRefreshing = true
        }

        DogsViewModel.State.Empty -> {
            fragment_dogs_list_empty_label.text = getString(R.string.fragment_dogs_list_no_data)
            fragment_dogs_list_empty_label.visibility = View.VISIBLE
            fragment_dogs_list.visibility = View.GONE
            fragment_dogs_list_swipe_layout.isRefreshing = false
        }

        is DogsViewModel.State.Loaded -> {
            fragment_dogs_list_empty_label.visibility = View.GONE
            fragment_dogs_list.visibility = View.VISIBLE
            // todo - use diffutils
            adapter.replaceData(state.albums)
            fragment_dogs_list_swipe_layout.isRefreshing = false
        }
    }
}
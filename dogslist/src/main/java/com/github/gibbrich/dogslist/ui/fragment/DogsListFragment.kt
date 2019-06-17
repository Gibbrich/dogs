package com.github.gibbrich.dogslist.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.github.gibbrich.dogslist.R
import com.github.gibbrich.dogslist.ui.adapter.DogsAdapter
import com.github.gibbrich.dogslist.ui.viewModel.DogsViewModel
import kotlinx.android.synthetic.main.fragment_dogs_list.*
import androidx.recyclerview.widget.GridLayoutManager

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val vm = ViewModelProviders.of(this).get(DogsViewModel::class.java)
        vm.state.observe(this, Observer(::handleStateNew))

        fragment_dogs_list_swipe_layout.setOnRefreshListener(vm::fetchAlbums)

        fragment_dogs_list.layoutManager = getGridLayoutManager()
        adapter = DogsAdapter(onDogClicked = vm::onDogClicked)
        fragment_dogs_list.adapter = adapter
    }

    private fun handleStateNew(state: DogsViewModel.State) {
        fragment_dogs_list_swipe_layout.isRefreshing = state.isLoading

        if (state.breeds.isEmpty()) {
            fragment_dogs_list_empty_label.visibility = View.VISIBLE
            fragment_dogs_list.visibility = View.GONE
        } else {
            fragment_dogs_list_empty_label.visibility = View.GONE
            fragment_dogs_list.visibility = View.VISIBLE
        }

        adapter.addDataToStart(state.breeds)
    }

    private fun getGridLayoutManager(): GridLayoutManager {
        val glm = GridLayoutManager(activity, 3)
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
}
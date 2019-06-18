package com.github.gibbrich.dogs.ui.viewModel

import androidx.lifecycle.MutableLiveData
import com.github.gibbrich.core.model.Breed
import com.github.gibbrich.core.repository.DogsRepository
import com.github.gibbrich.core.utils.schedulersIoToMain
import com.github.gibbrich.dogs.di.DI
import javax.inject.Inject

class DogsViewModel: BaseViewModel() {
    companion object {
        private const val PHOTOS_TO_FETCH = 10
    }

    val state = MutableLiveData<State>()

    @Inject
    internal lateinit var dogsRepository: DogsRepository

    init {
        DI.appComponent.inject(this)
        state.value = State()
    }

    fun fetchAlbums() {
        val isCacheDirty = state.value?.isFirstPhotoLoad?.not() ?: false
        safeSubscribe {
            dogsRepository
                .getRandomBreeds(PHOTOS_TO_FETCH, isCacheDirty)
                .schedulersIoToMain()
                .doOnSubscribe {
                    state.value = state.value?.copy(isLoading = true)
                }
                .subscribe(this::handleAnswer, this::handleError)
        }
    }

    private fun handleAnswer(breeds: List<Breed>) {
        state.value = State(
            isFirstPhotoLoad = false,
            breeds = breeds
        )
    }

    private fun handleError(err: Throwable) {
        state.value = state.value?.copy(
            isLoading = false,
            isLoadError = true
        )
    }

    data class State(
        val isLoading: Boolean = false,
        val isLoadError: Boolean = false,
        val isFirstPhotoLoad: Boolean = true,
        val breeds: List<Breed> = emptyList()
    )
}
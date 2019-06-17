package com.github.gibbrich.dogslist.ui.viewModel

import androidx.lifecycle.MutableLiveData
import com.github.gibbrich.core.manager.Navigator
import com.github.gibbrich.core.model.Breed
import com.github.gibbrich.core.repository.DogsRepository
import com.github.gibbrich.core.utils.schedulersIoToMain
import com.github.gibbrich.dogslist.di.DI
import javax.inject.Inject

class DogsViewModel: BaseViewModel() {
    companion object {
        private const val PHOTOS_TO_FETCH = 10
    }

    val state = MutableLiveData<State>()

    @Inject
    internal lateinit var dogsRepository: DogsRepository

    @Inject
    internal lateinit var navigator: Navigator

    init {
        DI.dogsComponent.inject(this)
        state.value = State()
    }

    fun onDogClicked(breed: Breed) = navigator.switchToBreedDetailScreen(breed)

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
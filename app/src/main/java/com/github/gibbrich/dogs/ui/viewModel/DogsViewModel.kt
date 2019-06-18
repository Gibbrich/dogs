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

    private var isFirstPhotosLoad = true

    init {
        DI.appComponent.inject(this)
        state.value = State.Empty
    }

    fun fetchAlbums() {
        safeSubscribe {
            dogsRepository
                .getRandomBreeds(PHOTOS_TO_FETCH, isFirstPhotosLoad.not())
                .schedulersIoToMain()
                .doOnSubscribe { state.value = State.Loading }
                .subscribe(this::handleAnswer, this::handleError)
        }
    }

    private fun handleAnswer(albums: List<Breed>) {
        if (isFirstPhotosLoad) {
            isFirstPhotosLoad = false
        }

        state.value = State.Loaded(albums)
    }

    private fun handleError(err: Throwable) {
        state.value = State.LoadError
    }

    sealed class State {
        object LoadError: State()
        object Loading: State()
        object Empty: State()
        data class Loaded(val albums: List<Breed>): State()
    }
}
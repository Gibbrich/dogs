package com.github.gibbrich.dogs.ui.viewModel

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

open class BaseViewModel: ViewModel() {
    protected val disposables = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()
        clear()
    }

    protected inline fun safeSubscribe(action: () -> Disposable) {
        disposables.add(action())
    }

    protected fun clear() = disposables.clear()
}
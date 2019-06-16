package com.github.gibbrich.core.utils

import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.SingleSource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

fun <T> Single<T>.schedulersIoToMain(): Single<T> {
    return subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
}
fun <T> Observable<T>.schedulersIoToMain(): Observable<T> {
    return subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
}

fun <T> T.toSingle(): Single<T> = Single.just(this)

fun <T, R> Single<T>.mapResult(
    onSuccess: (T) -> R,
    onError: (Throwable) -> R
): Single<R> {
    return map(onSuccess).onErrorReturn(onError)
}

fun <T, R> Single<T>.flatMapResult(
    onSuccess: (T) -> SingleSource<R>,
    onError: (Throwable) -> SingleSource<R>
): Single<R> {
    return flatMap(onSuccess).onErrorResumeNext(onError)
}
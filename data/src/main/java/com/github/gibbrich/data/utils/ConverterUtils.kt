package com.github.gibbrich.data.utils

import android.util.Log

inline fun <T, R> maybeConvert(item: T?, converter: (T) -> R): R? =
        if (item != null) {
            try {
                converter.invoke(item)
            } catch (e: ConvertException) {
                Log.e("maybeConvert", e.message, e)
                null
            }
        } else {
            null
        }

fun <T> getOrDie(item: T?, binding: String): T =
        item ?: throw ConvertException("'$binding' must not be null")

inline fun <E, T: Any> Iterable<E>.mapOrSkip(convertFun: (E) -> T?): List<T> = this.mapNotNull {
    maybeConvert(
        it,
        convertFun
    )
}
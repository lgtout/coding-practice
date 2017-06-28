package com.lagostout.common

fun <T> List<T>.takeIfNotLast(): List<T> {
    return this.take(this.size - 1)
}

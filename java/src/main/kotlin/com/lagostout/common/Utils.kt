package com.lagostout.common

fun <T> List<T>.takeNotLast(): List<T> {
    return this.take(this.size - 1)
}

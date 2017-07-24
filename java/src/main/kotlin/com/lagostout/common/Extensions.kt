package com.lagostout.common

import org.apache.commons.math3.random.RandomDataGenerator

fun <T> List<T>.takeIfNotLast(): List<T> {
    return this.take(this.size - 1)
}

fun RandomDataGenerator.nextInt(range: IntRange): Int {
    return nextInt(range.start, range.endInclusive)
}

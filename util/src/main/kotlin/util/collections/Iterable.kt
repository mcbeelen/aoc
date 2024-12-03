package util.collections

import kotlin.collections.Iterable

fun Iterable<Int>.maxOrDefault(default: Int = Int.MIN_VALUE): Int {

    return this.max() ?: default
}

fun Iterable<Int>.minOrDefault(default: Int = Int.MAX_VALUE): Int {
    return this.min() ?: default
}

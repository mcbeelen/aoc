package util.collections

import kotlin.collections.Iterable

internal fun Iterable<Int>.maxOrDefault(default: Int = Int.MIN_VALUE): Int {
    return this.maxOrNull() ?: default
}

internal fun Iterable<Int>.minOrDefault(default: Int = Int.MAX_VALUE): Int {
    return this.minOrNull() ?: default
}

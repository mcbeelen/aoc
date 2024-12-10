package util.collections

import java.util.LinkedHashSet

class MultiMap<K, V> {
    val map = HashMap<K, MutableSet<V>>()
    fun put(key: K, value: V) {
        if (!map.containsKey(key)) {
            map[key] = LinkedHashSet()
        }
        map[key]?.add(value)
    }

    fun putAll(key: K, values: Iterable<V>) {
        values.forEach { put(key, it) }
    }

    fun get(key: K): Set<V> {
        if (!map.containsKey(key)) {
            return setOf()
        }
        return map[key]!!
    }

    fun containsKey(key: K): Boolean = map.contains(key)
    fun getKeys() = map.keys

    override fun toString(): String {
        return this.map.toString()
    }
}
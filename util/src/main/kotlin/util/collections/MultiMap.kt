package util.collections

class MultiMap<K, V> {
    val map = HashMap<K, MutableSet<V>>()
    fun put(key: K, value: V) {
        if (!map.containsKey(key)) {
            map[key] = HashSet()
        }
        map[key]?.add(value)
    }

    fun get(key: K): Set<V> {
        if (!map.containsKey(key)) {
            return setOf()
        }
        return map[key]!!
    }

    fun containsKey(key: K): Boolean = map.contains(key)
    fun getKeys() = map.keys
}
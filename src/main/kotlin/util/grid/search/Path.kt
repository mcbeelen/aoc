package util.grid.search

data class Path<V>(val vertices: List<V> = emptyList()) where V : Vertex<V> {
    fun calculateDistance() = vertices.last().distanceFromSource
    fun contains(vertex: V) = vertices.contains(vertex)
    fun extendWith(vertex: V) = copy(vertices + vertex)
}

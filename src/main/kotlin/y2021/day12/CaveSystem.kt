package y2021.day12

import util.grid.search.Edge
import util.grid.search.Graph
import util.grid.search.ReadableZobraistKey
import util.grid.search.Vertex
import util.grid.search.ZobraistKey

class CaveSystem(private val connections: ArrayList<Connection>) : Graph<Cave, Connection>() {
    override fun findNeighbours(vertex: Cave): List<Connection> {
        return connections.filter {
            it.origin == vertex || it.destination == vertex
        }
    }
}

class Cave(val name: String) : Vertex<Cave>() {

    val isSmallCave by lazy { name.all { it.isLowerCase() } }
    val isBigCave by lazy { name.all { it.isUpperCase() } }

    override fun buildZobristKey(): ZobraistKey {
        return ReadableZobraistKey(name)
    }
}

class Connection(origin: Cave, destination: Cave, distance: Int = 1) : Edge<Cave>(origin, destination, distance)
package y2021.day12

import util.grid.search.Path
import util.puzzle.AdventOfCodePuzzle

class PassagePathing(
    testInput: String = "",
    val maximumNumberOfVisitsToSmallCave : Int = 0) : AdventOfCodePuzzle(testInput) {

    override fun solvePartOne(): Int {

        val caves = HashMap<String, Cave>()
        val connections = ArrayList<Connection>()

        input.forEach {
            val originName = it.substringBefore("-")
            val origin = caves.computeIfAbsent(originName) { Cave(originName) }

            val destinationName = it.substringAfter("-")
            val destination = caves.computeIfAbsent(destinationName) { Cave(destinationName) }

            connections.add(Connection(origin, destination))
        }

        val caveSystem = CaveSystem(connections)

        val start = caves.getValue("start")
        val initialPath = Path<Cave>().extendWith(start)

        val exploreCaves = exploreCaves(caveSystem, start, initialPath)

        val uniquePaths = exploreCaves.map { path -> path.vertices.joinToString { it.name } }.toSortedSet()
        return uniquePaths.size
    }

    private fun exploreCaves(
        caveSystem: CaveSystem,
        currentVertex: Cave,
        pathFromStartToCurrentVertex: Path<Cave>) : List<Path<Cave>> {

        val foundPaths = emptyList<Path<Cave>>().toMutableList()
        val potentialNextVertexes = caveSystem.findNeighbours(currentVertex)
        potentialNextVertexes.forEach {
            if (it.origin == currentVertex) {
                exploreFurther(pathFromStartToCurrentVertex, foundPaths, caveSystem, it.destination)
            }
            if (it.destination == currentVertex) {
                exploreFurther(pathFromStartToCurrentVertex, foundPaths, caveSystem, it.origin)
            }
        }

        return foundPaths

    }

    private fun exploreFurther(
        pathFromStartToCurrentVertex: Path<Cave>,
        foundPaths: MutableList<Path<Cave>>,
        caveSystem: CaveSystem,
        targetCave: Cave
    ) {
        if (targetCave.name == "start") {
            return
        }

        val extendedPath = pathFromStartToCurrentVertex.extendWith(targetCave)
        if (targetCave.name == "end") {
            foundPaths.add(extendedPath)
        } else if (
             (targetCave.isBigCave || isAllowedToVisitSmallTargetCave(pathFromStartToCurrentVertex, targetCave)
        )) {
            foundPaths.addAll(exploreCaves(caveSystem, targetCave, extendedPath))
        }
    }

    private fun isAllowedToVisitSmallTargetCave(
        pathFromStartToCurrentVertex: Path<Cave>,
        targetCave: Cave
    ) : Boolean {
        if (maximumNumberOfVisitsToSmallCave == 0) {
            return pathFromStartToCurrentVertex.vertices.none { it.name == targetCave.name }
        }
        if (pathFromStartToCurrentVertex.vertices.count { it.name == targetCave.name } == 0) {
            return true
        }
        return pathFromStartToCurrentVertex.vertices
            .filter { it.isSmallCave }
            .groupingBy { it.name }
            .eachCount()
            .none { it.value > maximumNumberOfVisitsToSmallCave }
    }

    override fun solvePartTwo(): Int {
        return solvePartOne()
    }
}

fun main() {
    PassagePathing().getAnswers()
}
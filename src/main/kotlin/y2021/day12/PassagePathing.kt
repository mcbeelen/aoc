package y2021.day12

import util.grid.search.Path
import util.puzzle.AdventOfCodePuzzle

class PassagePathing(
    testInput: String = "",
    val maximumNumberOfVisitsToSmallCave : Int = 0) : AdventOfCodePuzzle(testInput) {

    override fun solvePartOne(): Int {

        val (caveSystem, start) = parseInputIntoCaveSystem()
        val initialPath = Path<Cave>().extendWith(start)
        val foundPaths = exploreCaves(caveSystem, start, initialPath)
        return foundPaths.size
    }

    private fun parseInputIntoCaveSystem(): Pair<CaveSystem, Cave> {
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
        return Pair(caveSystem, start)
    }

    private fun exploreCaves(
        caveSystem: CaveSystem,
        currentCave: Cave,
        pathFromStartToCurrentVertex: Path<Cave>) : List<Path<Cave>> {

        val foundPaths = emptyList<Path<Cave>>().toMutableList()
        val connectionsToCurrentCave = caveSystem.findNeighbours(currentCave)
        connectionsToCurrentCave.forEach {
            if (it.origin == currentCave) {
                exploreFurther(pathFromStartToCurrentVertex, foundPaths, caveSystem, it.destination)
            }
            if (it.destination == currentCave) {
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
        } else if (targetCave.isBigCave ||
                   isAllowedToVisitSmallTargetCave(pathFromStartToCurrentVertex, targetCave)) {
            foundPaths.addAll(exploreCaves(caveSystem, targetCave, extendedPath))
        }
    }

    private fun isAllowedToVisitSmallTargetCave(
        pathFromStartToCurrentVertex: Path<Cave>,
        targetCave: Cave ) : Boolean {

        if (maximumNumberOfVisitsToSmallCave == 0) {
            return pathFromStartToCurrentVertex.vertices.none { it.name == targetCave.name }
        }

        return isFirstVisitToTargetCave(pathFromStartToCurrentVertex, targetCave)
               || isFirstSmallCaveToBeVisitedTwice(pathFromStartToCurrentVertex)
    }

    private fun isFirstVisitToTargetCave(
        pathFromStartToCurrentVertex: Path<Cave>,
        targetCave: Cave
    ) = pathFromStartToCurrentVertex.vertices.count { it.name == targetCave.name } == 0

    private fun isFirstSmallCaveToBeVisitedTwice(pathFromStartToCurrentVertex: Path<Cave>) =
        pathFromStartToCurrentVertex.vertices
            .filter { it.isSmallCave }
            .groupingBy { it.name }
            .eachCount()
            .none { it.value > maximumNumberOfVisitsToSmallCave }


    override fun solvePartTwo(): Int {
        return solvePartOne()
    }
}

fun main() {
    PassagePathing().getAnswers()
}
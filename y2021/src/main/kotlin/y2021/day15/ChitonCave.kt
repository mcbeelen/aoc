package y2021.day15

import util.grid.Direction
import util.grid.Direction.*
import util.grid.Grid
import util.grid.ORIGIN
import util.grid.ScreenCoordinate
import util.grid.parseToGrid
import util.puzzle.AdventOfCodePuzzle
import java.util.*
import kotlin.Int.Companion.MAX_VALUE
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

data class Position(val screenCoordinate: ScreenCoordinate, val lowestRiskOfPath : Int = MAX_VALUE)

class ChitonCave(testInput: String = "", val shouldEnlarge: Boolean = false) : AdventOfCodePuzzle(testInput) {

    val grid = parseToGrid(input) { it.code.toString().toInt() - 48 }
    val enlargedGrid : Grid<Int> by lazy {
        if (shouldEnlarge) {
            return@lazy grid
        } else {
            return@lazy grid
        }
    }

    protected val unexploredPositions: Queue<ScreenCoordinate> = ArrayDeque()



    override fun solvePartOne(): Int {

        val exploredPaths = HashMap<ScreenCoordinate, List<Position>>()
        exploredPaths[ORIGIN] = listOf(Position(ORIGIN, 0))
        unexploredPositions.offer(ORIGIN.next(RIGHT))
        unexploredPositions.offer(ORIGIN.next(DOWN))

        while (unexploredPositions.isNotEmpty()) {
            val nextPosition = unexploredPositions.poll()

            val potentialPaths = Direction.values()
                .map { nextPosition.next(it) }
                .filter { exploredPaths.containsKey(it) }



            val riskOfPathWithLowestRisk : Int = potentialPaths.minOf {
                exploredPaths.getValue(it).last().lowestRiskOfPath
            }
            val pathWithLeastRisk = potentialPaths.map { Pair(it, exploredPaths.getValue(it)) }
                .first {it.second.last().lowestRiskOfPath == riskOfPathWithLowestRisk }

            val exploredPosition = Position(pathWithLeastRisk.first, riskOfPathWithLowestRisk + grid.getValue(nextPosition))
            val previousPositions = ArrayList<Position>()
            previousPositions.addAll(pathWithLeastRisk.second)
            previousPositions.add(exploredPosition)

            exploredPaths[nextPosition] = previousPositions

            // TODO: Backtracking!!

            val newReachablePosition = Direction.values()
                .map { nextPosition.next(it) }
                .filter { grid.containsKey(it) }
                .filter { !exploredPaths.containsKey(it) }
                .filter { !unexploredPositions.contains(it) }
                .sorted()

            newReachablePosition.forEach {
                unexploredPositions.offer(it)
            }
        }


        return exploredPaths.getValue(grid.bottomRight()).last().lowestRiskOfPath
    }

    override fun solvePartTwo(): Int {
        TODO("Solve me")
    }
}



fun main() {
    ChitonCave().getAnswers()
}
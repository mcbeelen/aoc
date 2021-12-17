package y2021.day15

import com.google.common.base.Stopwatch
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

data class Position(
    val screenCoordinate: ScreenCoordinate,
    val lowestRiskOfPath : Int = MAX_VALUE,
    val entryFrom: Direction = LEFT
)

class ChitonCave(testInput: String = "", private val shouldEnlarge: Boolean = false) : AdventOfCodePuzzle(testInput) {

    private val grid = parseToGrid(input) { it.code.toString().toInt() - 48 }
    val enlargedGrid : Grid<Int> by lazy {
        val gridToConstruct =  Grid<Int>()
        if (shouldEnlarge) {
            val sortedGrid = grid.toSortedMap()
            val gridWith = grid.getWidth()
            val gridHeight = grid.getHeight()
            (0 until 5).forEach { columnIndex ->
                val leftOffset = gridWith * columnIndex
                (0 until 5).forEach { rowIndex ->
                    val topOffset = gridHeight * rowIndex
                    sortedGrid.forEach {
                        val left = it.key.left + leftOffset
                        val top = it.key.top + topOffset
                        val targetCoordinate = ScreenCoordinate(left, top)
                        gridToConstruct[targetCoordinate] = increaseRisk(it.value, columnIndex, rowIndex)
                    }
                }
            }
            return@lazy gridToConstruct
        } else {
            return@lazy grid
        }
    }

    private fun increaseRisk(original: Int, columnIndex: Int, rowIndex: Int): Int {
        val increasedValue = original + columnIndex + rowIndex
        if (increasedValue < 10) {
            return increasedValue
        }
        return (increasedValue + 1) % 10
    }


    override fun solvePartOne(): Int {
        return findTotalLowestRisk(grid)
    }

    override fun solvePartTwo(): Int {
        return findTotalLowestRisk(enlargedGrid)
    }
}

fun findTotalLowestRisk(gridToSearch: Grid<Int>): Int {



    val unexploredPositions: Queue<ScreenCoordinate> = ArrayDeque()
    val exploredPaths = HashMap<ScreenCoordinate, List<Position>>()
    exploredPaths[ORIGIN] = listOf(Position(ORIGIN, 0))
    unexploredPositions.offer(ORIGIN.next(RIGHT))
    unexploredPositions.offer(ORIGIN.next(DOWN))

    while (unexploredPositions.isNotEmpty()) {
        val nextPosition = unexploredPositions.poll()

        val potentialPaths = Direction.values()
            .map { nextPosition.next(it) }
            .filter { exploredPaths.containsKey(it) }


        val riskOfPathWithLowestRisk: Int = potentialPaths.minOf {
            exploredPaths.getValue(it).last().lowestRiskOfPath
        }
        val pathWithLeastRisk = potentialPaths.map { Pair(it, exploredPaths.getValue(it)) }
            .first { it.second.last().lowestRiskOfPath == riskOfPathWithLowestRisk }

        val previousPositions = ArrayList<Position>()
        previousPositions.addAll(pathWithLeastRisk.second)
        val previousCoordinate = pathWithLeastRisk.second.last().screenCoordinate
        val entryFrom : Direction = nextPosition.from(previousCoordinate)

        val exploredPosition =
            Position(nextPosition,
                riskOfPathWithLowestRisk + gridToSearch.getValue(nextPosition),
                entryFrom)
        previousPositions.add(exploredPosition)

        exploredPaths[nextPosition] = previousPositions


        val positionsToReview: Queue<Pair<ScreenCoordinate, ScreenCoordinate>> = ArrayDeque()
        Direction.values().filter { it != entryFrom }
            .map { nextPosition.next(it) }
            .filter { exploredPaths.containsKey(it) }
            .forEach { positionsToReview.offer(Pair(it, nextPosition)) }
        while (positionsToReview.isNotEmpty()) {
            val (positionToReview, reviewEntryFrom) = positionsToReview.poll()
            val existingShortestPath = exploredPaths.getValue(positionToReview)
            val riskOfExistingPath = existingShortestPath.last().lowestRiskOfPath

            val alternativePath = exploredPaths.getValue(reviewEntryFrom)
            val riskOfAlternativePath =  alternativePath.last().lowestRiskOfPath + gridToSearch.getValue(positionToReview)

            if (riskOfAlternativePath < riskOfExistingPath) {

                val alternativePreviousPositions = ArrayList<Position>()
                alternativePreviousPositions.addAll(alternativePath)

                val alternativeEntryFrom : Direction = positionToReview.from(reviewEntryFrom)

                val reviewedPosition =
                    Position(positionToReview,
                        riskOfAlternativePath,
                        alternativeEntryFrom)
                alternativePreviousPositions.add(reviewedPosition)

                exploredPaths[positionToReview] = alternativePreviousPositions

                Direction.values().filter { it != alternativeEntryFrom }
                    .map { positionToReview.next(it) }
                    .filter { exploredPaths.containsKey(it) }
                    .forEach { positionsToReview.offer(Pair(it, positionToReview)) }

            }

        }

        val newReachablePosition = values()
            .map { nextPosition.next(it) }
            .filter { gridToSearch.containsKey(it) }
            .filter { !exploredPaths.containsKey(it) }
            .filter { !unexploredPositions.contains(it) }
            .sorted()

        newReachablePosition.forEach {
            unexploredPositions.offer(it)
        }
    }


    return exploredPaths.getValue(gridToSearch.bottomRight()).last().lowestRiskOfPath
}


fun main() {
    ChitonCave().getAnswers()
}
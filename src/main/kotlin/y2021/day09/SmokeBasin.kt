package y2021.day09

import util.collections.Queue
import util.grid.ScreenCoordinate
import util.grid.parseToGrid
import util.puzzle.AdventOfCodePuzzle
import kotlin.collections.HashSet

typealias Height = Int
typealias Basin = Set<Point>
data class Point(val location: ScreenCoordinate, val height: Height) {
    constructor(data: Pair<ScreenCoordinate, Int>) : this(data.first, data.second)
}

class SmokeBasin(testInput: String = "") : AdventOfCodePuzzle(testInput) {

    private val heightMap = parseToGrid(input) { it.toString().toInt() }

    override fun solvePartOne(): Int = findLowPoints().sumOf { it.height + 1 }

    private fun findLowPoints(): List<Point> = heightMap.entries
        .map { Point(it.toPair()) }
        .filter { isLowPoint(it) }

    private fun isLowPoint(candidate: Point) =
        heightMap.valuesOfNeighbors(candidate.location).all { candidate.height < it }

    override fun solvePartTwo(): Int {

        return findLowPoints()
            .map { exploreBasin(it) }
            .map { it.size }
            .sorted()
            .takeLast(3)
            .reduce { acc, i -> acc * i }
    }


    fun exploreBasin(startingPoint: Point) : Basin {
        val basin = HashSet<Point>()
        val pointsToExplore = Queue<Point>()
        pointsToExplore.enqueue(startingPoint)

        while (pointsToExplore.isNotEmpty()) {
            val currentPoint = pointsToExplore.dequeue()
            if (currentPoint.height < 9) {
                basin.add(currentPoint)
                heightMap.allNeighbors(currentPoint.location)
                    .map { Point(it) }
                    .filter { it.height < 9 }
                    .filter { !basin.contains(it) }
                    .filter { !pointsToExplore.contains(it) }
                    .forEach { pointsToExplore.enqueue(it) }
            }
        }

        return basin

    }
}

fun main() {
    SmokeBasin().getAnswers()
}
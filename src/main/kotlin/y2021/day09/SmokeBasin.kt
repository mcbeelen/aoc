package y2021.day09

import util.grid.ScreenCoordinate
import util.grid.parseToGrid
import util.puzzle.AdventOfCodePuzzle

class SmokeBasin(testInput: String = "") : AdventOfCodePuzzle(testInput) {

    private val heightMap = parseToGrid(input) { it.toString().toInt() }


    override fun solvePartOne(): Int =
        heightMap.entries
            .filter { isLowPoint(it) }
            .sumOf { it.value + 1 }

    private fun isLowPoint(candidate: MutableMap.MutableEntry<ScreenCoordinate, Int>) =
        heightMap.neighbors(candidate.key).all { candidate.value < it }

    override fun solvePartTwo(): Int {
        TODO("Solve me")
    }
}

fun main() {
    SmokeBasin().getAnswers()
}
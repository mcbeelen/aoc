package y2024.day06

import util.grid.*
import util.grid.Turn.RIGHT
import util.puzzle.AdventOfCodePuzzle

class GuardGallivant(testInput: String = "") : AdventOfCodePuzzle(testInput) {

    private val mapOfTheLab: Grid<Char> by lazy { parseToGrid(input) { it } }

    override fun solvePartOne(): Int {
        val currentPosition = mapOfTheLab.entries.single { it.value == '^' }.key
        val guard = GridWalker(currentPosition)
        mapOfTheLab[currentPosition] = '.'

        val visitedPositions = mutableSetOf<ScreenCoordinate>()
        visitedPositions.add(currentPosition)

        while (mapOfTheLab.contains(guard.getNextPosition())) {
            if (isObstacleDirectInFrontOfGuard(guard)) {
                guard.turn(RIGHT)
            } else {
                guard.move {
                    visitedPositions.add(it)
                }
            }
        }

        return visitedPositions.size
    }

    private fun isObstacleDirectInFrontOfGuard(guard: GridWalker) =
        mapOfTheLab.getValue(guard.getNextPosition()) == '#'


    override fun solvePartTwo(): Int {
        TODO("Solve me")
    }
}

fun main() {
    GuardGallivant().getAnswers()
}
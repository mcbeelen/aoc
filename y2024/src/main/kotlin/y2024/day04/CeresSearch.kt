package y2024.day04

import util.grid.*
import util.grid.Direction.*
import util.puzzle.AdventOfCodePuzzle

class CeresSearch(testInput: String = "") : AdventOfCodePuzzle(testInput) {

    val gridLetters: Grid<Char> by lazy { parseToGrid(input) { it } }

    override fun solvePartOne(): Int {
        return gridLetters
            .filter { it.value == 'X' }
            .map { countXmassesStartingAt( it ) }
                .sum()
    }

    private fun countXmassesStartingAt(entry: Map.Entry<ScreenCoordinate, Char>) : Int {
        return EIGHT_COMPASS_DIRECTIONS.count {
            doesItSpellsXmas(entry.key, it)
        }


    }

    private fun doesItSpellsXmas(coordinateOfX: ScreenCoordinate, vector: Vector) : Boolean {
        return doesHaveLetterAt(coordinateOfX.next(vector), 'M') &&
                doesHaveLetterAt(coordinateOfX.next(vector.times(2)), 'A') &&
                doesHaveLetterAt(coordinateOfX.next(vector.times(3)), 'S')

    }

    private fun doesHaveLetterAt(coordinate: ScreenCoordinate, letter: Char) =
        gridLetters.getOrElse(coordinate) { return false } == letter

    override fun solvePartTwo(): Int {
        return gridLetters
            .filter { it.value == 'A' }
            .count { isCenterOfXMAS( it.key ) }

    }

    private fun isCenterOfXMAS(coordinateOfA: ScreenCoordinate) :Boolean {
        return values().any {
            doesSpellMass(coordinateOfA, it)

        }
    }

    private fun doesSpellMass(coordinateOfA: ScreenCoordinate, direction: Direction): Boolean {
        return when (direction) {
            DOWN -> hasTwoLetters('M', UP, coordinateOfA) && hasTwoLetters('S', DOWN, coordinateOfA)
            UP -> hasTwoLetters('M', DOWN, coordinateOfA) && hasTwoLetters('S', UP, coordinateOfA)
            RIGHT -> hasTwoLetters('M', LEFT, coordinateOfA) && hasTwoLetters('S', RIGHT, coordinateOfA)
            LEFT -> hasTwoLetters('M', RIGHT, coordinateOfA) && hasTwoLetters('S', LEFT, coordinateOfA)
        }
    }

    private fun hasTwoLetters(letter: Char, direction: Direction, coordinateOfA: ScreenCoordinate) : Boolean {
        return when (direction) {
            UP -> doesHaveLetterAt(coordinateOfA.next(UP_LEFT), letter) && doesHaveLetterAt(coordinateOfA.next(UP_RIGHT), letter)
            RIGHT -> doesHaveLetterAt(coordinateOfA.next(DOWN_RIGHT), letter) && doesHaveLetterAt(coordinateOfA.next(UP_RIGHT), letter)
            DOWN -> doesHaveLetterAt(coordinateOfA.next(DOWN_RIGHT), letter) && doesHaveLetterAt(coordinateOfA.next(DOWN_LEFT), letter)
            LEFT ->  doesHaveLetterAt(coordinateOfA.next(UP_LEFT), letter) && doesHaveLetterAt(coordinateOfA.next(DOWN_LEFT), letter)
        }

    }

    val UP_LEFT = Vector(-1, -1)
    val UP_RIGHT = Vector(+1, -1)
    val DOWN_LEFT = Vector(-1, +1)
    val DOWN_RIGHT = Vector(+1, +1)
}

fun main() {
    CeresSearch().getAnswers()
}
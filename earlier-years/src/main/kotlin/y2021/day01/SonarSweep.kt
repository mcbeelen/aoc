package y2021.day01

import util.puzzle.AdventOfCodePuzzle

class SonarSweep(testInput: String = "") : AdventOfCodePuzzle(testInput) {

    override fun solvePartOne(): Int {
        return inputAsInts()
            .windowed(2)
            .count { it.last() > it.first() }
    }

    override fun solvePartTwo(): Int {
        return inputAsInts()
            .windowed(4)
            .count { it.last() > it.first() }
    }
}

fun main() {
    SonarSweep().getAnswers()
}



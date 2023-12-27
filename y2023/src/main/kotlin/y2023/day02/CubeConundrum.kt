package y2023.day02

import util.puzzle.AdventOfCodePuzzle


enum class Color {
    RED,
    GREEN,
    BLUE
}

data

class CubeConundrum(testInput: String = "") : AdventOfCodePuzzle(testInput) {

    /**
     * Which games are possible with only 12 red cubes, 13 green cubes, and 14 blue cubes
     */
    override fun solvePartOne(): Int {
        println(input[0])

        //parse input it game with round
        // map rounds in to max cubes per color
        // filter game on max cubes < available cubes
        // map to game its
        // fold into sum.
        TODO("Solve me")
    }

    override fun solvePartTwo(): Int {
        TODO("Solve me")
    }
}

fun main() {
    CubeConundrum().getAnswers()
}

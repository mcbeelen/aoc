package y2022.day01

import util.puzzle.AdventOfCodePuzzle

class CalorieCounting(testInput: String = "") : AdventOfCodePuzzle(testInput) {

    override fun solvePartOne(): Int {
        val blockPerElf = puzzleInput.split("\n\n")
        return blockPerElf.maxOf { sumCaloriesCarriedByElf(it) }
    }

    private fun sumCaloriesCarriedByElf(block: String)= block.split("\n")
        .map { Integer.parseInt(it) }
        .sum()


    override fun solvePartTwo(): Int {
        val blockPerElf = puzzleInput.split("\n\n")
        return blockPerElf.map { sumCaloriesCarriedByElf(it) }
            .sortedDescending()
            .take(3)
            .sum()
    }
}

fun main() {
    CalorieCounting().getAnswers()
}
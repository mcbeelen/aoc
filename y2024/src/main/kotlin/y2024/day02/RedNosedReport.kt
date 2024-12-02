package y2024.day02

import util.puzzle.AdventOfCodePuzzle

class RedNosedReport(testInput: String = "") : AdventOfCodePuzzle(testInput) {

    override fun solvePartOne(): Int {
        return input.count { isSafe(it) }
    }

    private fun isSafe(report: String) : Boolean {
        val windowed = report.split(" ")
            .map { it.toInt() }
            .windowed(2)
        return checkIfLevelsAreSafe(windowed)


    }

    private fun checkIfLevelsAreSafe(windowed: List<List<Int>>): Boolean {
        val first = windowed[0]
        return when {
            ((first[0] - first[1]) < 0) -> isIncreasing(windowed)
            ((first[0] - first[1]) > 0) -> isDecreasing(windowed)
            else -> false
        }
    }

    private fun isDecreasing(windowed: List<List<Int>>): Boolean = windowed.all {
        it.get(0) > it.get(1) &&
                it.get(0) - it.get(1) <= 3
    }

    private fun isIncreasing(windowed: List<List<Int>>): Boolean {
        return windowed.all {
            it.get(0) < it.get(1) &&
                    it.get(1) - it.get(0) <= 3
        }
    }

    override fun solvePartTwo(): Int {
        val safeReports = input.filter { isSafe(it) }
        val unsafeReports = input.filter { ! isSafe(it) }

        val reportsWithSingleError = unsafeReports
            .filter { isSafeWithoutOneEntry(it) }
        return safeReports.size + reportsWithSingleError.size
    }

    private fun isSafeWithoutOneEntry(unsafeReport: String) : Boolean {
        val levels = unsafeReport.split(" ")
            .map { it.toInt() }



        return levels.withIndex().any {
           checkLevels(it, levels)
        }
    }

    private fun checkLevels(it: IndexedValue<Int>, levels: List<Int>) : Boolean {
        val levelWithoutPotentialErrors = levels.toMutableList()
        levelWithoutPotentialErrors.removeAt(it.index)

        return checkIfLevelsAreSafe(levelWithoutPotentialErrors.windowed(2))
    }
}

fun main() {
    RedNosedReport().getAnswers()
}
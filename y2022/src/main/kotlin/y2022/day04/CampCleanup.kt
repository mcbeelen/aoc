package y2022.day04

import util.puzzle.AdventOfCodePuzzle
import java.lang.Integer.parseInt

class CampCleanup(testInput: String = "") : AdventOfCodePuzzle(testInput) {

    private val sectorAssignments : List<Pair<IntRange, IntRange>> by lazy {
        splitInputIntoPairOfSectorAssignments()
    }
    override fun solvePartOne(): Int {

        return sectorAssignments
            .count { it.first.fullyContains(it.second) || it.second.fullyContains(it.first) }
    }

    override fun solvePartTwo(): Int {
        return sectorAssignments
            .count { it.first.hasOverlap(it.second) || it.second.hasOverlap(it.first) }
    }

    private fun splitInputIntoPairOfSectorAssignments() = input
        .map { it.split(",") }
        .map { Pair(toIntRange(it[0]), toIntRange(it[1])) }


    private fun toIntRange(sectorAssignment: String): IntRange {
        val split = sectorAssignment.split("-")
        return parseInt(split[0]) .. parseInt(split[1])

    }


}

private fun IntRange.hasOverlap(other: IntRange): Boolean {
    return this.any { other.contains(it) }

}

private fun IntRange.fullyContains(other: IntRange): Boolean {
    return this.contains(other.first) && this.contains(other.last)
}

fun main() {
    CampCleanup().getAnswers()
}
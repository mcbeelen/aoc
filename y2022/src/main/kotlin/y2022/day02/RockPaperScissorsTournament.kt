package y2022.day02

import util.puzzle.AdventOfCodePuzzle

class RockPaperScissorsTournament(testInput: String = "") : AdventOfCodePuzzle(testInput) {

    override fun solvePartOne(): Int {
        return loadTodaysInput { Pair(parseTheirs(it), parseMineForPartOne(it)) }
            .sumOf { it.second.score + outcome(it) }
    }

    private fun outcome(round: Pair<HandShape, HandShape>): Int {
        return when {
            round.first == round.second -> 3
            round.second.isWinnerOver(round.first) -> 6
            else -> 0
        }
    }

    private fun parseTheirs(round: String): HandShape {
        return when (round.get(0)) {
            'A' -> HandShape.Rock
            'B' -> HandShape.Paper
            'C' -> HandShape.Scissors
            else -> throw IllegalArgumentException()
        }
    }

    private fun parseMineForPartOne(round: String): HandShape {
        return when (round.get(2)) {
            'X' -> HandShape.Rock
            'Y' -> HandShape.Paper
            'Z' -> HandShape.Scissors
            else -> throw IllegalArgumentException()
        }
    }

    private fun parseMineForPartTwo(theirs: HandShape, mine: Char): HandShape {
        return when (mine) {
            'X' -> theirs.getLoser()
            'Y' -> theirs
            'Z' -> theirs.getWinner()
            else -> throw IllegalArgumentException()
        }
    }

    override fun solvePartTwo(): Int {
        return loadTodaysInput {

            val theirs = parseTheirs(it)
            return@loadTodaysInput Pair(theirs, parseMineForPartTwo(theirs, it.get(2))) }
            .sumOf { it.second.score + outcome(it) }
    }
}

enum class HandShape(val score: Int) {
    Rock(1),
    Paper(2),
    Scissors(3);

    fun isWinnerOver(other: HandShape): Boolean {
        return when (this) {
            Rock -> other == Scissors
            Paper -> other == Rock
            Scissors -> other == Paper
        }
    }

    fun getLoser() = when(this) {
        Rock -> Scissors
        Paper -> Rock
        Scissors -> Paper
    }

    fun getWinner() = when(this) {
        Rock -> Paper
        Paper -> Scissors
        Scissors -> Rock
    }
}

fun main() {
    RockPaperScissorsTournament().getAnswers()
}
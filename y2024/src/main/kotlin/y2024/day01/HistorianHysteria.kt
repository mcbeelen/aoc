package y2024.day01

import util.puzzle.AdventOfCodePuzzle
import kotlin.math.abs

class HistorianHysteria(testInput: String = "") : AdventOfCodePuzzle(testInput) {

    override fun solvePartOne(): Int {
        val notes = input.map { splitIntoPairOfLocationIds(it) }
        val firsts = notes.map { it.first }.sorted()
        val seconds = notes.map { it.second }.sorted()
        return firsts
                .zip(seconds)
                .map { abs(it.second - it.first) }
            .sum()

    }

    private fun splitIntoPairOfLocationIds(note: String) : Pair<Int,Int>{
        val first = note.substringBefore(" ").toInt()
        val second = note.substringAfterLast(" ").toInt()
        return Pair(first, second)
    }

    override fun solvePartTwo(): Int {
        val notes = input.map { splitIntoPairOfLocationIds(it) }
        val firsts = notes.map { it.first }.sorted()
        val seconds = notes.map { it.second }.sorted()
        val counts = HashMap<Int, Int>()
        for (element in seconds) {
            counts[element] = counts.getOrDefault(element, 0) + 1
        }

        println(counts)
        return firsts
            .map { it * counts.getOrDefault(it, 0) }
            .sum()
    }
}

fun main() {
    HistorianHysteria().getAnswers()
}
package util.puzzle

import com.google.common.base.Stopwatch
import util.input.loadLines
import java.lang.Integer.parseInt
import java.util.concurrent.TimeUnit

abstract class AdventOfCodePuzzle(
    val testInput: String = ""
    ) {

    val input : List<String> by lazy {
        if (testInput.isBlank()) {
            return@lazy loadLines(this, "input")
        }
        return@lazy testInput.lines()
    }

    open fun getAnswerForPartOne() : String {
        return solvePartOne().toString()
    }

    open fun getAnswerForPartTwo() : String {
        return solvePartTwo().toString()
    }
    open fun solvePartOne() : Int {
        TODO("No solution for part one yet")
    }

    open fun solvePartTwo() : Int {
        TODO("No solution for part two yet")
    }

    fun inputAsInts() = input.map { parseInt(it) }

    fun getAnswers() {
        val stopwatch = Stopwatch.createStarted()
        println("Part one: " + getAnswerForPartOne())
        println("  (solving this part took: ${stopwatch.elapsed(TimeUnit.MILLISECONDS)}ms.")
        println("Part Two: " + getAnswerForPartTwo())
        println("  (solving both took: ${stopwatch.elapsed(TimeUnit.MILLISECONDS)}ms.")
    }
}

package util.puzzle

import com.google.common.base.Stopwatch
import util.input.loadLines
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



    open fun solvePartOne() : String {
        TODO("No solution for part one yet")
    }

    open fun solvePartTwo() : String {
        TODO("No solution for part two yet")
    }

    fun getAnswers() {
        val stopwatch = Stopwatch.createStarted()
        println("Part one: " + solvePartOne())
        println("  (solving took: ${stopwatch.elapsed(TimeUnit.MILLISECONDS)}ms.")
        println("Part Two: " + solvePartTwo())
        println("  (solving took: ${stopwatch.elapsed(TimeUnit.MILLISECONDS)}ms.")

    }



}

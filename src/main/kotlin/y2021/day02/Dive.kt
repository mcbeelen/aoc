package y2021.day02

import util.grid.ORIGIN
import util.grid.Vector
import util.puzzle.AdventOfCodePuzzle

class Dive(testInput: String = "") : AdventOfCodePuzzle(testInput) {


    override fun solvePartOne(): String {
        return solvePartOneFromLines(input).toString()
    }

    override fun solvePartTwo(): String {
        var currentPosition = ORIGIN
        var aim = Vector(1, 0)

        input.forEach {
            val command = it.substringBefore(' ');
            val units = it.substringAfter(' ').trim().toInt()

            when (command) {
                "forward" -> currentPosition = currentPosition.next(aim.times(units))
                "down" -> aim = aim.copy(top = aim.top + units)
                "up" -> aim = aim.copy(top = aim.top - units)
           }
        }

        return "${currentPosition.left * currentPosition.top}"

    }
}



fun solvePartOneFromLines(lines: Iterable<String>): Int {
    val input = lines.map {
        Pair(it.substringBefore(' '), it.substringAfter(' ').trim().toInt())
    }
    val groupBy = input.groupBy { it.first }
        .mapValues { it.value.map { it.second }.sum() }

    val forward = groupBy.getValue("forward")
    val down = groupBy.getValue("down")
    val up = groupBy.getValue("up")

    return (down - up) * forward
}

fun main() {
    Dive().getAnswers()
}
package y2021.day02

import util.input.loadLines
import util.input.parseInput

class Dive {
    fun solvePartOne() {
        val input = loadLines("/y2021/day02/input")

        println(solveFromLines(input));
    }
}

fun solveIt(testInput: String): Int {
    val lines = parseInput(testInput);
    return solveFromLines(lines)
}

private fun solveFromLines(lines: Iterable<String>): Int {
    val input = lines.map {
        Pair(it.substringBefore(' '), it.substringAfter(' ').trim().toInt())
    }
    val groupBy = input.groupBy { it.first }
        .mapValues { it.value.map { it.second }.sum() }

    val forward = groupBy.getValue("forward")
    println(forward)
    val down = groupBy.getValue("down")
    println(down)
    val up = groupBy.getValue("up")
    println(up)

    return (down - up) * forward
}

fun main() {
    Dive().solvePartOne()

}
package y2021.day13

import util.grid.Direction
import util.grid.Direction.*
import util.grid.Grid
import util.grid.ScreenCoordinate
import util.grid.parseXcommaY
import util.grid.plotGrid
import util.puzzle.AdventOfCodePuzzle
import kotlin.collections.HashSet

class TransparentOrigami(testInput: String = "") : AdventOfCodePuzzle(testInput) {

    val dotsOnTransparantPaper : Set<ScreenCoordinate> by lazy {
        val foundDots = HashSet<ScreenCoordinate>().toMutableSet()
        input.forEach {
            if (it.contains(",")) {
                foundDots.add(parseXcommaY(it))
            }
        }
        return@lazy foundDots
    }

    val foldInstructions : List<Instruction> by lazy {
        val foundInstructions = input.mapNotNull {
            if (it.startsWith("fold along")) {
                buildInstruction(it)
            } else { null }
        }
        return@lazy foundInstructions
    }

    override fun solvePartOne(): Int {
        val instruction = foldInstructions[0]
        val foldedPaper = performFold(dotsOnTransparantPaper, instruction)
        return foldedPaper.size
    }

    override fun solvePartTwo(): Int {
        val foldedPaper = foldInstructions.fold(dotsOnTransparantPaper) { unfoldedPaper, instruction -> performFold(unfoldedPaper, instruction) }
        val grid = Grid<Boolean>()
        foldedPaper.forEach { grid[it] = true }
        plotGrid(grid) { '#'}
        return foldedPaper.size
    }

    private fun performFold(unfoldedPaper: Set<ScreenCoordinate>, instruction: Instruction): Set<ScreenCoordinate> {
        if (instruction.direction == UP) {
            return foldUp(unfoldedPaper, instruction.line)
        } else {
            return foldLeft(unfoldedPaper, instruction.line)
        }
    }

    private fun foldUp(unfoldedPaper: Set<ScreenCoordinate>, line: Int): Set<ScreenCoordinate> {

        val dotsAboveTheFold = unfoldedPaper.filter { it.top < line }.toMutableSet()
        val foldedDots = unfoldedPaper
            .filter { it.top > line }
            .map { it.copy(top = line - (it.top - line)) }
        dotsAboveTheFold.addAll(foldedDots)
        return dotsAboveTheFold
    }


    private fun foldLeft(unfoldedPaper: Set<ScreenCoordinate>, line: Int): Set<ScreenCoordinate> {
        val dotsLeftOfTheFold = unfoldedPaper.filter { it.left < line }.toMutableSet()
        val foldedDots = unfoldedPaper
            .filter { it.left > line }
            .map { it.copy(left = line - (it.left - line)) }
        dotsLeftOfTheFold.addAll(foldedDots)
        return dotsLeftOfTheFold
    }
}

fun main() {
    TransparentOrigami().getAnswers()
}

data class Instruction(val direction: Direction, val line : Int)

fun buildInstruction(description: String): Instruction {
    val direction = if (description.contains("y=")) { UP } else { LEFT }
    val line = description.substringAfter("=").trim().toInt()
    return Instruction(direction, line)
}
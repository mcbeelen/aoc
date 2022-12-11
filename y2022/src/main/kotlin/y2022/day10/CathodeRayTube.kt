package y2022.day10

import util.grid.Grid
import util.grid.ScreenCoordinate
import util.grid.plotGrid
import util.puzzle.AdventOfCodePuzzle

class CathodeRayTube(testInput: String = "") : AdventOfCodePuzzle(testInput) {


    override fun getAnswerForPartOne(): String {
        var x = 1L
        var cycleCounter = 0L;
        val signalStrengths = ArrayList<Long>()

        val programInstructions = input.asIterable()

        programInstructions.forEach {
            cycleCounter++
            checkAndSaveSignalStrength(cycleCounter, x, signalStrengths)
            val operation = it.substringBefore(" ")
            when (operation) {
                "addx" -> {
                    cycleCounter++
                    checkAndSaveSignalStrength(cycleCounter, x, signalStrengths)
                    val value = it.substringAfter(" ").toLong()
                    x += value
                }
                else -> /* no-op */ {
                }
            }
        }

        return signalStrengths.sum().toString()
    }

    private fun checkAndSaveSignalStrength(cycleCounter: Long, x: Long, signalStrengths: MutableList<Long>) {
        if ((cycleCounter % 40).toInt() == 20) {
            signalStrengths.add(x * cycleCounter)
        }
    }

    override fun getAnswerForPartTwo() : String {
        val crtScreen = Grid<Char>()

        var x = 1
        var cycleCounter = 0;

        val programInstructions = input.asIterable()

        programInstructions.forEach {
            drawPixel(cycleCounter, x, crtScreen)
            cycleCounter++

            val operation = it.substringBefore(" ")
            when (operation) {
                "addx" -> {
                    drawPixel(cycleCounter, x, crtScreen)
                    cycleCounter++
                    val value = it.substringAfter(" ").toInt()
                    x += value
                }
                else -> /* no-op */ {
                }
            }
        }

        plotGrid(crtScreen, '?') { it }

        return "EHPZPJGL"

    }

    private fun drawPixel(cycleCounter: Int, x: Int, crtScreen: Grid<Char>) {
        val left = cycleCounter % 40
        val top = cycleCounter / 40

        val screenCoordinate = ScreenCoordinate(left, top)
        if ((x - 1..x + 1).contains(left)) {
            crtScreen[screenCoordinate] = '█'
        } else {
            crtScreen[screenCoordinate] = ' '
        }
    }
}

fun main() {
    CathodeRayTube().getAnswers()
}
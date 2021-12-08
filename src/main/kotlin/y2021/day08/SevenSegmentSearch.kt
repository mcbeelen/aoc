package y2021.day08

import com.github.shmvanhouten.adventofcode2021.day08.decipherOutput
import com.github.shmvanhouten.adventofcode2021.day08.toSignal
import util.puzzle.AdventOfCodePuzzle

class SevenSegmentSearch(testInput: String = "") : AdventOfCodePuzzle(testInput) {

    enum class Segment {
        A,B,C,D,E,F,G
    }

    override fun solvePartOne(): Int {
        return input
            .map { splitInput(it.trim()).second }
            .map { countSimpleDigits(it) }
            .sum()
    }

    private fun countSimpleDigits(inputPart: String) =
        inputPart
            .split(" ")
            .count { isSimple(it) }

    private val lengthOfSimpleDigits = arrayOf(2, 3, 4, 7)
    private fun isSimple(it: String) = lengthOfSimpleDigits.contains(it.length)

    override fun solvePartTwo(): Int {
        return input.map { toSignal(it) }
            .sumOf { decipherOutput(it) }
    }
}

fun splitInput(line: String) : Pair<String, String> {
    val signalPatternInput = line.substringBefore(" | ")
    val fourDigitOutput = line.substringAfter(" | ")
    return Pair(signalPatternInput, fourDigitOutput)
}

fun main() {
    SevenSegmentSearch().getAnswers()
}

typealias WireSegmentConnection = HashMap<Char, SevenSegmentSearch.Segment>
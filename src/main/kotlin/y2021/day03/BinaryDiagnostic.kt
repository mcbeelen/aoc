package y2021.day03

import util.countLetters
import util.puzzle.AdventOfCodePuzzle
import util.transpose
import java.lang.Integer.parseInt

class BinaryDiagnostic(testInput: String = "") : AdventOfCodePuzzle(testInput) {

    override fun solvePartOne(): Int {
        val transposedInput = transpose(input)
        val (gammaRate, epsilonRate) = extract(transposedInput)
        return (gammaRate * epsilonRate)
    }



    override fun solvePartTwo(): Int {
        val oxygenGeneratorRating = filterForFrequencyBasedRating(input) {
            if (it.getValue('1') >= it.getValue('0')) {
                '1'
            } else {
                '0'
            }
        }
        val co2ScrubberRating = filterForFrequencyBasedRating(input) {
            if (it.getValue('0') <= it.getValue('1')) {
                '0'
            } else {
                '1'
            }
        }
        val oxygenGeneratorRatingValue = parseInt(oxygenGeneratorRating, 2)
        val co2ScrubberRatingValue = parseInt(co2ScrubberRating, 2)
        return oxygenGeneratorRatingValue * co2ScrubberRatingValue
    }

    private tailrec fun filterForFrequencyBasedRating(
        remainingAvailableBinaryNumbers: List<String>,
        currentPosition : Int = 0,
        predicate: (Map<Char, Int>) -> Char): String {

        val countPerDigit = remainingAvailableBinaryNumbers
            .map { it[currentPosition] }
            .groupingBy { it }
            .eachCount()

       val selector = predicate.invoke(countPerDigit)

        val filter = remainingAvailableBinaryNumbers.filter { it[currentPosition] == selector }
        if (filter.size == 1) {
            return filter.single()
        }
        return filterForFrequencyBasedRating(filter, currentPosition + 1, predicate)
    }

    private fun extract(transposedInput: List<String>): Pair<Int, Int> {
        val countedLetters = transposedInput.map {
            countLetters(it)
        }
        val mostCommonBits = countedLetters.map {
            if (it.getValue('1') > it.getValue('0') ) {
                '1'
            } else {
                '0'
            }
        }.joinToString("")

        val leastCommonBits = countedLetters.map {
            if (it.getValue('0') > it.getValue('1') ) {
                '0'
            } else {
                '1'
            }
        }.joinToString("")

        val gammaRate = parseInt(mostCommonBits, 2)
        val epsilon = parseInt(leastCommonBits, 2)

        return Pair(gammaRate, epsilon)
    }
}

fun main() {
    BinaryDiagnostic().getAnswers()
}
package y2021.day14

import util.puzzle.AdventOfCodePuzzle

data class Polymer(val elementPairCount : Map<String, Long>, val duplicateElementCount: Map<Char, Long>)

fun parsePolymer(template: String) : Polymer {
    val elementPairCount = template.windowed(2).groupingBy { it }.eachCount().mapValues { it.value.toLong() }
    val duplicateElementCount = template.drop(1).dropLast(1).groupingBy { it }.eachCount().mapValues { it.value.toLong() }
    return Polymer(elementPairCount, duplicateElementCount)
}

data class PairInsertionStep(val inputElementPair: String, val outputElementPairs: Iterable<String>, val duplicatedElement: Char)

fun parsePairInsertionStep(pairInsertionRule: String) : PairInsertionStep {
    val (elements, insert) = pairInsertionRule.split(" -> ")
    val insertedElement = insert[0]
    val firstElementPair = "${pairInsertionRule[0]}$insertedElement"
    val secondElementPair = "$insertedElement${pairInsertionRule[1]}"
    val outputElementPairs = listOf(firstElementPair, secondElementPair)
    val step = PairInsertionStep(elements, outputElementPairs, insertedElement)
    return step
}
class ExtendedPolymerization(testInput: String = "") : AdventOfCodePuzzle(testInput) {
    val polymer = parsePolymer(input[0])

    val pairInsertionRules = input.drop(2)
        .map { parsePairInsertionStep(it) }
        .map { Pair(it.inputElementPair, it) }
        .toMap()

    override fun getAnswerForPartOne(): String {
        return expandPolymerAndFindFactors(10).toString()
    }

    override fun getAnswerForPartTwo(): String {
        return expandPolymerAndFindFactors(40).toString()
    }

    private fun expandPolymerAndFindFactors(numberOfIterations: Int): Long {
        var nextSequence = polymer
        (0 until numberOfIterations).forEach {
            nextSequence = performInsertionStep(nextSequence)
        }

        val frequencyPerLetter =
            nextSequence.elementPairCount.flatMap { listOf(Pair(it.key[0], it.value), Pair(it.key[1], it.value)) }
                .groupBy { it.first }
                .mapValues { sumValues(it.value) }
                .toMutableMap()

        nextSequence.duplicateElementCount.forEach { duplicateCount ->
            frequencyPerLetter.compute(duplicateCount.key) { _, oldValue ->
                if (oldValue != null) {
                    return@compute oldValue - duplicateCount.value
                } else {
                    return@compute Long.MIN_VALUE
                }
            }
        }


        val quantityOfMostCommonElement = frequencyPerLetter.maxOf { it.value }
        val quantityOfLeastCommonElement = frequencyPerLetter.minOf { it.value }

        return quantityOfMostCommonElement - quantityOfLeastCommonElement
    }

    private fun sumValues(values: List<Pair<Char, Long>>) = values.sumOf { it.second }

    private fun performInsertionStep(inputPolymer: Polymer): Polymer {

        val nextElementPairCount = HashMap<String, Long>()
        val nextDuplicateElementCount = inputPolymer.duplicateElementCount.toMutableMap()

        inputPolymer.elementPairCount.forEach { element ->
            val rule = pairInsertionRules.getValue(element.key)
            rule.outputElementPairs.forEach {
                nextElementPairCount.compute(it) { _: String, oldValue: Long? ->
                    return@compute if (oldValue == null) { element.value } else { oldValue + element.value }
                }
            }
            nextDuplicateElementCount.compute(rule.duplicatedElement) { _:Char, oldValue: Long? ->
                return@compute if (oldValue == null) { element.value } else {
                    oldValue + element.value
                }
            }
        }


        return Polymer(nextElementPairCount, nextDuplicateElementCount)
    }
}

fun main() {
    ExtendedPolymerization().getAnswers()
}
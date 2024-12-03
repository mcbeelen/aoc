package y2024.day03

import util.puzzle.AdventOfCodePuzzle

class MullItOver(testInput: String = "") : AdventOfCodePuzzle(testInput) {



    override fun solvePartOne(): Int {
        return performAllMultiplications(input.joinToString { it })
//        return input
//            .map { performAllMultiplications(it) }
//            .sum()
    }

    private fun performAllMultiplications(rowFromMemory: String) : Int {
        val potentialMultiplications = rowFromMemory.split("mul")

        return potentialMultiplications
            .filter { isNotCorrupted(it) }
            .map { performMultiplication(it)}
            .sum()
    }

    private fun performMultiplication(it: String): Int {
        val factorA = it.substringAfter("(").substringBefore(",").toInt()
        val factorB = it.substringAfter(",").substringBefore(")").toInt()

        return factorA * factorB
    }


    fun isNotCorrupted(multiplication: String): Boolean {
        return REAL_MULTIPLICATION.matches(multiplication)
    }

    override fun solvePartTwo(): Int {

        return performAllMultiplications(stripDisabledBlocks(input.joinToString { it }))
//        return  input.joinToString { it }
//            .map { stripDisabledBlocks(it) }
//            .map { performAllMultiplications(it) }
//            .sum()
    }

    private fun stripDisabledBlocks(rowFromMemory: String): String {
        val builder = mutableListOf<String>()
        var remainingText = rowFromMemory
        while (remainingText.contains(DO_NOT_INSTRUCTION)) {
            val positionOfDo = remainingText.lastIndexOf(DO_INSTRUCTION)
            val positionOfDont = remainingText.lastIndexOf(DO_NOT_INSTRUCTION)
            println("Processing DONT $positionOfDont vs DO $positionOfDo")
            if (positionOfDont < positionOfDo) {
                val enabledInstructions = remainingText.substring(positionOfDo)
                println("Added to DO: ${enabledInstructions}")
                builder.add(enabledInstructions)
                remainingText = remainingText.substringBeforeLast(DO_INSTRUCTION)
            } else {
                println("Discarding disabled _${remainingText.substringAfterLast(DO_NOT_INSTRUCTION)}_")
                remainingText = remainingText.substringBeforeLast(DO_NOT_INSTRUCTION)
            }
            // println("${remainingText}")
        }
        println("Added to DO (last)" + remainingText)
        builder.add(remainingText)
        builder.reverse()
        return builder.joinToString { it }

    }
}

val DO_INSTRUCTION = "do()"
val DO_NOT_INSTRUCTION = "don't()"
val REAL_MULTIPLICATION = Regex("^\\(\\d{1,3},\\d{1,3}\\).*")

fun main() {
    MullItOver().getAnswers()
}
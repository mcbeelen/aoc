package y2022.day13

import util.puzzle.AdventOfCodePuzzle

typealias SignalPair = Pair<String, String>

class DistressSignal(testInput: String = "") : AdventOfCodePuzzle(testInput) {

    override fun solvePartOne(): Int {

        val inputReader = input.asSequence()
        val signalPairs = (0 .. input.size / 3).map {
            val blockPerSignalPair = inputReader
                .drop(it * 3)
                .take(2).toList()
            return@map parseToSignalPair(blockPerSignalPair)
        }

        return signalPairs
            .mapIndexed { index, pair -> if (isInTheRightOrder(pair)) index else 0 }
            .sumOf { it }
    }

    private fun isInTheRightOrder(signalPair: SignalPair): Boolean {


        return true

    }

    private fun parseToSignalPair(blockPerSignalPair: List<String>) = SignalPair(blockPerSignalPair.get(0), blockPerSignalPair.get(1))

    override fun solvePartTwo(): Int {
        TODO("Solve me")
    }
}

fun main() {
    DistressSignal().getAnswers()
}
package y2022.day06

import util.puzzle.AdventOfCodePuzzle

class TuningTrouble(testInput: String = "") : AdventOfCodePuzzle(testInput) {

    override fun solvePartOne()=  findMarker(4)

    override fun solvePartTwo()=  findMarker(14)

    private fun findMarker(lenghtOfMarker: Int): Int {
        val datastream = readSingleLineInput()
        datastream.windowed(lenghtOfMarker).forEachIndexed { index, candidateMarker ->
            if (candidateMarker.hasAllDistinctCharacters()) {
                return index + lenghtOfMarker
            }
        }
        return Int.MIN_VALUE
    }
}

private fun String.hasAllDistinctCharacters()= this.asIterable().toSet().size == this.length

fun main() {
    TuningTrouble().getAnswers()
}
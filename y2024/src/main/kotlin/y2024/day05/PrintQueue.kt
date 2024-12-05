package y2024.day05

import util.collections.MultiMap
import util.puzzle.AdventOfCodePuzzle
class PageOrderingComparator(val rules: MultiMap<Int, Int>) : Comparator<Int> {
    override fun compare(first: Int, second: Int): Int {
        if (rules.containsKey(second)) {
            if (rules.get(second).contains(first)) {
                return -1
            }
        }
        return 1
    }
}

class PrintQueue(testInput: String = "") : AdventOfCodePuzzle(testInput) {

    val forbiddenPageBehind = MultiMap<Int, Int>()
    val pageOrderingRules : Comparator<Int> by lazy { PageOrderingComparator(forbiddenPageBehind) }
    override fun solvePartOne(): Int {
        populateForbiddenPages()

        val pagesToProduce = input
            .filter { it.contains(",") }
            .map { extractPageNumbersInUpdate(it) }
        return pagesToProduce
            .filter{ isInTheRightOrder(it)}
            .map { extractMiddlePageNumber(it) }
            .sum()
    }

    private fun extractPageNumbersInUpdate(it: String) = it.split(",")
        .map { it.trim().toInt() }

    private fun populateForbiddenPages() {
        input
            .filter { it.contains("|") }
            .forEach {
                val trimmed = it.trim()
                val first = trimmed.substringBefore("|").trim().toInt()
                val second = trimmed.substringAfter("|").trim().toInt()
                forbiddenPageBehind.put(second, first)
            }
    }

    private fun extractMiddlePageNumber(pageNumbers: List<Int>) : Int {
        return pageNumbers[(pageNumbers.size - 1) /2].toInt()
    }

    fun isInTheRightOrder(pageNumbersInUpdate: List<Int>) : Boolean {
        return pageNumbersInUpdate.mapIndexed { index, i -> Pair(index, i) }
            .none { hasAnyForbiddenPageBehindPage(it.first, it.second, pageNumbersInUpdate) }
    }

    private fun hasAnyForbiddenPageBehindPage(index: Int, pageNumber: Int, pageNumbers: List<Int>): Boolean {
        if (!forbiddenPageBehind.containsKey(pageNumber)) {
            return false
        }
        val forbiddenPages = forbiddenPageBehind.get(pageNumber)
        val pagesBehindCurrent = pageNumbers.drop(index)
        return forbiddenPages.any { pagesBehindCurrent.contains(it) }
    }

    override fun solvePartTwo(): Int {
        populateForbiddenPages()

        val pagesToProduce = input
            .filter { it.contains(",") }
            .map { extractPageNumbersInUpdate(it) }
        return pagesToProduce
            .filter{ !isInTheRightOrder(it)}
            .map { sortIntoTheRightOrder(it) }
            .map { extractMiddlePageNumber(it) }
            .sum()
    }

    private fun sortIntoTheRightOrder(update: List<Int>) : List<Int> {
        return update.sortedWith(pageOrderingRules)
    }


}

fun main() {
    PrintQueue().getAnswers()
}
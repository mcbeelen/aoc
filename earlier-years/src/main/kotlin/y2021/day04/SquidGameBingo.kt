package y2021.day04

import com.google.common.collect.HashBasedTable
import com.google.common.collect.Table
import util.puzzle.AdventOfCodePuzzle
import java.lang.Integer.parseInt

class BingoBoard(input : List<String>) {

    private val valuesOnBoard : Table<Int, Int, Int> = HashBasedTable.create()
     init {
         input.forEachIndexed { rowIndex, line ->
             line.trim().split("\\s+".toRegex())
                 .map { parseInt(it) }
                 .forEachIndexed { colIndex, value ->
                     valuesOnBoard.put(rowIndex, colIndex, value)
                 }
         }
     }

    fun hasBingo() : Boolean {
        return valuesOnBoard.rowKeySet().any { rowIndex -> valuesOnBoard.row(rowIndex).all { it.value == Int.MIN_VALUE } } ||
               valuesOnBoard.columnKeySet().any { colIndex -> valuesOnBoard.column(colIndex).all { it.value == Int.MIN_VALUE } }
    }

    fun mark(numberDrawn: Int) {

        valuesOnBoard.cellSet().filter { it.value == numberDrawn }
            .forEach { valuesOnBoard.put(it.rowKey!!, it.columnKey!!, Int.MIN_VALUE) }
    }

    fun getAllUnmarkedNumbers(): List<Int> {
        return valuesOnBoard.values().filter { it != Int.MIN_VALUE }
    }

}

class SquidGameBingo(testInput: String = "") : AdventOfCodePuzzle(testInput) {

    private val bingoBoards: List<BingoBoard> by lazy {
        var bingoBoardsInput = input.drop(2)
        val bingoBoards = ArrayList<BingoBoard>()
        while (bingoBoardsInput.isNotEmpty()) {
            bingoBoards.add(
                BingoBoard(bingoBoardsInput.take(5))
            )
            bingoBoardsInput = bingoBoardsInput.drop(6)

        }
        return@lazy bingoBoards
    }

    private val numbersDrawn: Iterator<Int> by lazy {
        input.first().split(',').map { parseInt(it) }.asSequence().iterator()
    }

    override fun solvePartOne(): Int {

        var lastCalledNumber = Int.MIN_VALUE
        while(bingoBoards.none { it.hasBingo() }) {
            lastCalledNumber = callNextNumber()
        }

        val winningBoard = bingoBoards.single { it.hasBingo() }
        val sumOfUnmarkedNumbers = winningBoard.getAllUnmarkedNumbers()
                                               .sum()

        return lastCalledNumber * sumOfUnmarkedNumbers
    }



    override fun solvePartTwo(): Int {
        var lastCalledNumber = Int.MIN_VALUE
        while(bingoBoards.count { ! it.hasBingo() } > 1) {
            lastCalledNumber = callNextNumber()
        }

        val losingBoard = bingoBoards.single { ! it.hasBingo() }
        while (!losingBoard.hasBingo()) {
            lastCalledNumber = numbersDrawn.next()
            losingBoard.mark(lastCalledNumber)
        }
        val sumOfUnmarkedNumbers = losingBoard.getAllUnmarkedNumbers()
            .sum()

        return lastCalledNumber * sumOfUnmarkedNumbers
    }

    private fun callNextNumber(): Int {
        val lastCalledNumber = numbersDrawn.next()
        bingoBoards.forEach {
            it.mark(lastCalledNumber)
        }
        return lastCalledNumber
    }
}

fun main() {
    SquidGameBingo().getAnswers()
}
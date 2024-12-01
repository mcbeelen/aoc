package y2024.day01

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.Ignore
import org.junit.Test
import kotlin.Int.Companion.MIN_VALUE

class HistorianHysteriaTest {

    @Test
    fun examplePartOne() {
        val puzzle = HistorianHysteria(testInput)
        assertThat(puzzle.solvePartOne(), equalTo(11))
    }

    @Test
    fun actualPartOne() {
        val puzzle = HistorianHysteria()
        assertThat(puzzle.solvePartOne(), equalTo(MIN_VALUE))
    }

    @Test
    fun examplePartTwo() {
        val puzzle = HistorianHysteria(testInput)
        assertThat(puzzle.solvePartTwo(), equalTo(31))
    }

    @Test
    fun actualPartTwo() {
        val puzzle = HistorianHysteria()
        assertThat(puzzle.solvePartTwo(), equalTo(MIN_VALUE))
    }

}


private const val testInput = """3   4
4   3
2   5
1   3
3   9
3   3"""
